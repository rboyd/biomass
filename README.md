# THIS REPOSITORY IS DEPRECATED -> biomass has [moved!](https://github.com/smnirven/biomass)


# biomass

> Det. Thorn: "Who bought you?"

> Hatcher: "You're bought as soon as they pay you a salary."

> -- *Soylent Green*

Drive [Amazon Mechanical Turk](http://mturk.com) from your Clojure apps.

# Configuration

Create a config.clj in your resources paths with a map with the following format:

```clojure
{:AWSAccessKeyId    "deadbeef"
 :SecretAccessToken "cafebabe"
}
```

# Usage

Amazon Mechanical Turk allocates jobs to humans in the form of "Human
Intelligence Tasks" or "HITs".

First create a HIT type and Layout using the [Requester UI](http://docs.aws.amazon.com/AWSMechTurk/latest/RequesterUI/Welcome.html).

Example of creating a HIT:

```clojure
(let [hit-type-id     "VCZVWLDJOTFFJXXQLGXZ"
      hit-layout-id   "WMYUHDBKJKNGOAMNCNMT"
      10-minutes      (* 10 60)
      available-for   10-minutes]
  (create-hit hit-type-id hit-layout-id available-for
    (hit-layout-form {"field1" "val1"
                      "field2" "val2"})))
```



## License

Copyright © 2013 Robert Boyd

Distributed under the Eclipse Public License, the same as Clojure.
