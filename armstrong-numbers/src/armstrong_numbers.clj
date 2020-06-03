(ns armstrong-numbers)

(defn armstrong?
  [n]
  (let [digits (map (comp read-string str) (str n))
        num-digits (count digits)
        digits-exponentiated (map #(Math/pow % num-digits) digits)]
    (== n (reduce + digits-exponentiated))))