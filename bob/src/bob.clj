(ns bob
  (:require [clojure.string :refer [trim upper-case lower-case]]))

(defn silent? [s]
  (= "" s))

(defn shouted? [s]
  (and (not (silent? s)) (= (upper-case s) s) (not (= (lower-case s) s))))

(defn question? [s]
  (= (last s) \?))

(defn response-for [s] ;; <- arglist goes here
  (let [t (trim s)]
    (cond
      (silent? t) "Fine. Be that way!"
      (and (shouted? t) (question? t)) "Calm down, I know what I'm doing!"
      (shouted? t) "Whoa, chill out!"
      (question? t) "Sure."
      :else "Whatever.")))
