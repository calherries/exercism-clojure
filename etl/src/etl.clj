(ns etl
  (:require [clojure.string :as string]))

(defn transform [data]
  (into {}
        (for [[k vs] data
              v vs]
          [(string/lower-case v) k])))

(transform {1 ["APPLE" "ARTICHOKE"], 2 ["BOAT" "BALLERINA"]})
