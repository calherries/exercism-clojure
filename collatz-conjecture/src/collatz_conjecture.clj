(ns collatz-conjecture)

(defn collatz [num] ;; <- arglist goes here
  (reduce (fn [n i]
            (cond
              (= n 1)   (reduced i)
              (even? n) (/ n 2)
              :else     (inc (* n 3))))
          num (range)))
