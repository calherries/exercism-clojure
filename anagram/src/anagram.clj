(ns anagram
  (:require [clojure.string :as string]))

(defn is-anagram [word prospect]
  (let [[lower-word lower-prospect] (map string/lower-case [word prospect])]
    (and
      (not= lower-word lower-prospect)
      (= (frequencies lower-word) (frequencies lower-prospect)))))

(defn anagrams-for [word prospect-list] ;; <- arglist goes here
  (filter (partial is-anagram word) prospect-list))
