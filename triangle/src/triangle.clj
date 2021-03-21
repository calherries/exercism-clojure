(ns triangle)

(defn is-valid? [a b c]
  (if (.contains [a b c] 0)
    false
    (let [[a b c] (sort (list a b c))]
      (< c (+ a b)))))

(defn equilateral? [a b c]
  (and (is-valid? a b c) (= a b c)))

(defn isosceles? [a b c]
  (if-not (is-valid? a b c)
    false
    (< (count (set (list a b c)))
       3)))

(defn scalene? [a b c]
  (if-not (is-valid? a b c)
    false
    (= (count (set (list a b c)))
       3)))