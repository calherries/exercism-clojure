(ns isbn-verifier)

(defn count-down 
  [n]
  (->> n
       range
       reverse
       (map inc)))

(defn char-to-int [c]
  (cond 
    (Character/isDigit c)
    ((comp read-string str) c)
    (= \X c)
    10))

(defn isbn? 
  [isbn]
  (let [
        multiplied-digits (->> isbn
                               (remove #{\-})
                               (map char-to-int)
                               (keep identity)
                               (#(map * (count-down (count %)) %)))
        mod-test (->> multiplied-digits
                      (reduce +)
                      (#(mod % 11))
                      (== 0))]
        (and mod-test (== 10 (count multiplied-digits)))))
