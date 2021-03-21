(ns say
  (:require [clojure.string :as str]))

(def specials
  {1  "one"
   2  "two"
   3  "three"
   4  "four"
   5  "five"
   6  "six"
   7  "seven"
   8  "eight"
   9  "nine"
   10 "ten"
   11 "eleven"
   12 "twelve"
   13 "thirteen"
   14 "fourteen"
   15 "fifteen"
   16 "sixteen"
   17 "seventeen"
   18 "eighteen"
   19 "nineteen"})

(def tens
  {2 "twenty"
   3 "thirty"
   4 "forty"
   5 "fifty"
   6 "sixty"
   7 "seventy"
   8 "eighty"
   9 "ninety"})

(defn number-without-zero [num]
  (cond
    (= num 0)
    nil

    (<= 1 num 19)
    (get specials num)

    (<= 20 num 99)
    (let [tens-digit (quot num 10)
          ones-digit (rem num 10)
          ones-text  (get specials ones-digit)
          tens-text  (str (get tens tens-digit)
                          (when (not-empty ones-text) "-"))]
      (str tens-text ones-text))

    (<= 100 num 999)
    (let [digit            (quot num 100)
          remaining-digits (rem num 100)
          remaining-text   (number-without-zero remaining-digits)]
      (str/join " " [(get specials digit)
                     "hundred"
                     remaining-text]))))

(defn number [num]
  (cond
    (= num 0)
    "zero"

    (not (<= 0 num 999999999999))
    (throw (IllegalArgumentException. "Must be a valid number."))

    :else
    (let [digits         (str num)
          num-digits     (count digits)
          [start rest]   (split-at (mod num-digits 3) digits)
          groups         (map (comp number-without-zero read-string #(apply str %))
                              (concat (when-not (empty? start) (list start)) (partition 3 rest)))
          group-and-size (map vector
                              (reverse groups)
                              '(nil "thousand" "million" "billion" "trillion"))]
      (->> group-and-size
           (filter #(not-empty (first %)))
           reverse
           (apply concat)
           (str/join " ")
           str/trimr))))
