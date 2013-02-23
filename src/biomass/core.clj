(ns biomass.core
  (:require [clj-http.client :as client]))

{:AWSAccessKeyId ""
 :Service ""
 :Operation ""          ; GetHIT
 :Signature ""
 :Timestamp ""
 :ResponseGroup ""
 :Version "2012-03-25"}

Operations:
[:CreateHit :GetAssignment :GetAssignmentsForHIT
 :GetHIT :GetRequestStatistic
 :GetRequesterWorkerStatistic :SearchHITS]

Response Groups:
[:Request :Minimal :HITDetail :HITQuestion
 :AssignmentFeedback :HITAssignmentSummary
 :Parameters]


(defn create-hit
  "You must specify review policies when you create a hit.

  There are two types of review policies, assignment-level
  and HIT-level:

  - Assignment-level is applied as soon as a Worker submits
  - HIT-level policy is applied when a HIT becomes reviewable

The Assignment-level Review Policy ScoreMyKnownAnswer/2011-09-01 and the HIT-level Review Policy SimplePlurality/2011-09-01 can be used in the same call to CreateHIT.

"
  [{hit-type :hit-type
                   question :question
                   :as options}]

  
  )
  


(defn create-hit [response-group]
  ""
