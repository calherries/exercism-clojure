(ns prime-factors)

(defn of [num]
  (loop [factors  [] ;; accumulates a list of factors. could be replaced with filtering a range, or consing onto a lazy
         divisor 2  ;; keeps track of the last found factor, because we should start searching from here
         num  num] ;; keeps track of the number to find factors for
    (if (= 1 num)
      factors
      (let [next (first (filter #(zero? (mod num %))
                              (iterate inc divisor)))]
        (recur (conj factors next)
               next
               (quot num next))))))
