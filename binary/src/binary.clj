(ns binary)

(defn to-decimal
  [binary]
  (reduce (fn [acc chr]
            (let [digit-number (Character/digit chr 2)]
              (if (= -1 digit-number)
                (reduced 0)
                (+ (* 2 acc)
                   (Character/digit chr 2)))))
          0
          binary))
