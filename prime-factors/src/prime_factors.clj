(ns prime-factors)

(defn factor? [num factor]
  (zero? (rem num factor)))

(defn prime? [num]
  (every? (complement #(factor? num %)) (range 2 num)))

(defn next-prime [start num]
  (first (filter (every-pred #(factor? num %) prime?) (range start (inc num)))))

(defn of [num]
  (let [iter (fn iter [prev num]
              (if (= 1 num)
                []
                (let [next (next-prime prev num)
                      remaining (quot num next)]
                  (cons next (iter next remaining)))))]
    (iter 2 num)))
