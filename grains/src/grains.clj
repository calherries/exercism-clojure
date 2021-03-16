(ns grains)

(defn square
  [n]
  (bigint (*' 2.0 (- n 1))))

(defn total
  []
  (- (bigint (*' 2.0 64.0))
     1))
