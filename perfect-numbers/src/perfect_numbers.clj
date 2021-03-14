(ns perfect-numbers)

(defn is-divisible-by [n x]
  (zero? (mod n x)))

(defn factors [n]
  (filter #(is-divisible-by n %)
          (range 1 n)))

(defn classify [n]
  (if (neg? n)
    (throw (IllegalArgumentException. "Negative number"))
    (let [aliquot-sum (apply + (factors n))]
      (case (compare aliquot-sum n)
        0 :perfect
        1 :abundant
        -1 :deficient))))
