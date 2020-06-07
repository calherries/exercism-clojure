(ns series)

(defn slices [string length]
  (if (zero? length)
    [""]
    (for [i (-> string
                count
                (- length)
                inc
                range)]
      (subs string i (+ i length)))))

(slices "abcde" 2)
