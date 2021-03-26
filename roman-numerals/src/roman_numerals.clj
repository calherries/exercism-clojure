(ns roman-numerals
  (:require [vvvvalvalval.supdate.api :as supd :refer [supdate]]))

(def numeral-to-number
  {\I 1
   \V 5
   \X 10
   \L 50
   \C 100
   \D 500
   \M 1000})

(def sorted-numeral-to-number-pairs
  (sort-by val > numeral-to-number))

(defn is-power-of-ten
  [x]
  (zero? (rem (Math/log10 x) 1)))

(is-power-of-ten 1)
(is-power-of-ten 10)
(is-power-of-ten 50)

(def numeral-groups-to-number-pairs
  (->> sorted-numeral-to-number-pairs
       (iterate rest)
       (take-while not-empty)
       (map (fn [lst]
              (let [[largest-symbol largest-value] (first lst)
                    power-of-ten-numerals (filter (comp is-power-of-ten second)
                                                  (rest lst))]
                (->> power-of-ten-numerals
                     (map (fn [[symbol value]]
                            [(str symbol largest-symbol) (- largest-value value)]))
                     reverse ;; this puts them in descending order
                     (cons [(str largest-symbol) largest-value])))))
       (apply concat)))

(take-while not-empty (iterate rest [1 2 3 4]))
(filter (every-pred is-power-of-ten) [1 5 10 50 100])

(defn numerals [number]
  (loop [number-remaining number
         numerals []]
           ;; find the largest valued numeral
           ;; subtract it's value from the number-remaining
           ;; conj it onto numerals
    (if (zero? number-remaining)
      (apply str numerals)
      (let [[next-numeral next-number]
            (first (filter #(<= (second %) number-remaining)
                         numeral-groups-to-number-pairs))]
        (recur (- number-remaining next-number)
               (conj numerals next-numeral))))))

(roman-numerals/numerals 1)
(roman-numerals/numerals 2)
(roman-numerals/numerals 3)
(roman-numerals/numerals 4)
(roman-numerals/numerals 5)
(roman-numerals/numerals 6)
(roman-numerals/numerals 9)
(roman-numerals/numerals 27)
(roman-numerals/numerals 48)
(roman-numerals/numerals 59)
(roman-numerals/numerals 93)
(roman-numerals/numerals 141)
(roman-numerals/numerals 163)
(roman-numerals/numerals 402)
(roman-numerals/numerals 575)
(roman-numerals/numerals 911)
(roman-numerals/numerals 1024)
(roman-numerals/numerals 3000)

(supdate [1 2 3] [[inc inc inc]])