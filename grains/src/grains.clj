(ns grains)

(defn square
  [n]
  (bigint (Math/pow 2 (- n 1))))

(defn total
  []
  (dec (bigint (Math/pow 2 64))))
