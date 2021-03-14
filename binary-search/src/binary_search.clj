(ns binary-search)

(defn middle [lst]
  (quot (- (count lst) 1) 2))

;; Iterative solution
(defn search-for [item lst]
  (loop [remaining lst
         left-idx 0]
    (if (empty? remaining)
      (throw (ex-info "not found" {})))
    (let [mid (middle remaining)
          mid-item (nth remaining mid)]
      (case (compare item mid-item)
        0 (+ mid left-idx)
        -1 (recur (take mid remaining) left-idx)
        1 (recur (drop (inc mid) remaining) (+ 1 mid left-idx))))))
