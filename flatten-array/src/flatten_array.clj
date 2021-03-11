(ns flatten-array)

(defn flatten [arr]
  (filter some?
        (reduce (fn [acc x]
                  (if (sequential? x)
                    (vec (concat acc (flatten x)))
                    (conj acc x)))
                []
                arr)))
