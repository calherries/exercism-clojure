(ns prime-factors)

(defn factor? [num factor]
  (zero? (rem num factor)))

(defn next-factor [start num]
  (first (filter #(factor? num %) (range start (inc num)))))

(defn of [num]
  (loop [{:keys [acc prev num]}
         {:acc  []
          :prev 2
          :num  num}]
    (if (= 1 num)
      acc
      (let [next (next-factor prev num)
            remaining (quot num next)]
        (recur {:acc (conj acc next)
                :prev next
                :num remaining})))))
