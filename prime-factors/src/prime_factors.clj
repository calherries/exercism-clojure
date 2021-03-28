(ns prime-factors)

(defn factor? [num factor]
  (zero? (rem num factor)))

(defn next-factor [start num]
  (first (filter #(factor? num %) (range start (inc num)))))

(defn of [num]
  (loop [{:keys [factors prev num]}
         {:factors  [] ;; accumulates a list of factors
          :prev 2  ;; keeps track of the last found factor, because we should start searching from here
          :num  num}] ;; keeps track of the number to find factors for
    (if (= 1 num)
      factors
      (let [next (next-factor prev num)
            remaining (quot num next)]
        (recur {:factors (conj factors next)
                :prev next
                :num remaining})))))
