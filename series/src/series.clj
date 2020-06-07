(ns series)

(defn slices [string length] ;; <- arglist goes here
  (if (= length 0)
    [""]
    (for [i (range (inc (- (count string) length)))]
      (apply str (subvec (vec string) i (+ i length))))))
