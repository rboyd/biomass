(defproject biomass "0.1.0-SNAPSHOT"
  :description "Boss people around. No suit required."
  :url "https://github.com/rboyd/biomass"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :resource-paths ["etc"]
  :plugins [[lein-expectations "0.0.7"]]
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [clj-http "0.6.4"]
                 [pasha "2.0"]
                 [clj-time "0.4.4"]
                 [expectations "1.4.33"]
                 [sonian/carica "1.0.2"]])
