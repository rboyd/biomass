(ns biomass.core-test
  (:use expectations
        biomass.core)
  (:import [org.joda.time DateTime]))

(expect (freeze-time (DateTime. 1) (build-request {:Operation "foo"} {:AWSAccessKeyId "abcd1234" :SecretAccessKey "zyx321"}))
        {:Signature "UOWphJGRB6sgdAuxq2/Ws0BeUoM=", :Timestamp "1969-12-31T18:00:00-06:00", :Operation "foo", :Service "AWSMechanicalTurkRequester", :Version "2012-03-25", :AWSAccessKeyId "abcd1234"})
