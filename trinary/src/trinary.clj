(ns trinary)

(defn to-decimal [num-str]
  (reduce (fn [acc chr]
            (let [next-digit (Character/digit chr 3)]
              (if (= -1 next-digit)
                (reduced 0)
                (+ next-digit
                   (* acc 3)))))
          0
          num-str))
