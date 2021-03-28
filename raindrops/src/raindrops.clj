(ns raindrops)

(defn is-factor [num quotient]
  (zero? (rem num quotient)))

(defn convert [num]
  (->> [[3 "Pling"]
        [5 "Plang"]
        [7 "Plong"]]
       (filter #(is-factor num (first %)))
       (map second)
       (#(if (empty? %)
           (str num)
           (apply str %)))))
