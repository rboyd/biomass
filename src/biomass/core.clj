(ns biomass.core
  (:require [clj-http.client :as client]
            [clj-time.local :refer [local-now format-local-time]]
            [pasha.authenticate :refer [hmac]]
            [carica.core :refer [config]]))

(defonce api-version "2012-03-25")

(defn get-hit
  "Given a HitID, returns a map to form an API request to the GetHIT
  operation."
  [hit-id]
  {:Operation "GetHIT"
   :HITId     hit-id})

(defn hit-layout-form
  "Given a map of {k1 v1 k2 v2} returns the HITLayoutParameter form."
  [params]
  (let [convert #(hash-map (str "HITLayoutParameter." %1 ".Name")  (first %2)
                           (str "HITLayoutParameter." %1 ".Value") (second %2))]
    (reduce merge (map convert (range) params))))

(defn create-hit
  "Given a HitTypeID and a list of Parameters, returns a map to form an API
  request to the CreateHIT operation."
  [hit-type-id hit-layout-id lifetime parameters]
  (merge {:Operation         "CreateHIT"
          :HITTypeId         hit-type-id
          :HITLayoutId       hit-layout-id
          :LifetimeInSeconds lifetime}
         (hit-layout-form parameters)))

(defn apply-timestamp [m]
  "Merges the current time into the input map."
  (merge m {:Timestamp (format-local-time (local-now) :date-time-no-ms)}))

(defn apply-signature
  "Calculates a cryptographic signature for the request by concatenating the
  values of the Service, Operation, and Timestamp parameters and then
  calculating an RFC 2104-compliant HMAC using the secret-access-key as the 'key'
  in base64 encoding. Result is then merged into the input map as the 'Signature'
  parameter."
  [{:keys [Service Operation Timestamp] :as m} secret-access-key]
  (->> (str Service Operation Timestamp)
       (hmac secret-access-key)
       (hash-map :Signature)
       (merge m)))

(defn build-request
  "Merges a resulting hash from an mturk operation with authentication
  credentials, applies timestamp, calculates signature, and issues HTTP
  request."
  [operation-hash config]
  (let [common-params {:Service "AWSMechanicalTurkRequester"
                       :Version api-version
                       :AWSAccessKeyId (config :AWSAccessKeyId)}]
    (-> (merge common-params operation-hash)
        apply-timestamp
        (apply-signature (config :SecretAccessKey)))))

(defn issue-request [params]
  (client/get "https://mechanicalturk.amazonaws.com/" {:query-params params}))
