(ns pascals-triangle)


(defn row-from-previous [previous]
  (cons 1 (->> (partition-all 2 1 previous)
               (map (partial apply +)))))

(comment
  (map row-from-previous [[1] [1 1] [1 2 1]]))

(def triangle
  (lazy-seq
   (cons [1]
         (map row-from-previous triangle))))

(defn row [n]
  (nth triangle (dec n)))
