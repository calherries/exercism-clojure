(ns pascals-triangle)

(def triangle
  (lazy-seq
   (cons [1]
         )))

(defn row-from-previous [previous]
  (loop [acc [1]
         remaining previous]
    (if (empty? remaining)
      acc
      (if (empty? (rest remaining))
        (conj acc 1)
        (recur (conj acc (+ (first previous) (second previous)))
               (rest previous))))))

(map row-from-previous [[1] [1 1] [1 2 1]])

(row-from-previous [1])

(defn row [] ;; <- arglist goes here
)
