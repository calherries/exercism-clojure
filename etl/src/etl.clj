(ns etl
  (:require [clojure.string :as string]))

(defn transform [data]
  (into {}
        (for [[k vs] data
              v vs]
          [(string/lower-case v) k])))

(def data {1 ["APPLE" "ARTICHOKE"], 2 ["BOAT" "BALLERINA"]})

(into {} (for [[k v] data] [(inc k) v]))

(let [coll [{:id 456 :x "hello"}
            {:id 641 :x "world"}
            {:id 941 :x "wide"}]]
  (into {} (map (juxt :id identity)) coll))

(reduce-kv (fn [m k v] (assoc m k (f v))))