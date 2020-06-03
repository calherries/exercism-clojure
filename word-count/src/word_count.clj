(ns word-count
  (:require [clojure.string :as str]))

 (defn word-count [s] ;; <- arglist goes here
  (->> s
       str/lower-case
       (re-seq #"\w+")
       (reduce #(merge-with + %1 {%2 1})
               {})))

