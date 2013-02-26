(ns biomass.core
  (:require [clj-http.client :as client]
            [carica.core :refer [config]]))

(defn create-hit []
  (println (config :AWSAccessKeyId)))
