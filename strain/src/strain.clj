(ns strain)

(defn retain [pred coll]
  (if (empty? coll)
    '()
    (if (pred (first coll))
      (lazy-seq (cons (first coll) (retain pred (next coll))))
      (retain pred (next coll)))))

(defn discard [pred coll]
  (if (empty? coll)
    '()
    (if-not (pred (first coll))
      (lazy-seq (cons (first coll) (discard pred (next coll))))
      (discard pred (next coll)))))
