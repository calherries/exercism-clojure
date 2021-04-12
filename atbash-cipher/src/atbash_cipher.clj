(ns atbash-cipher)

(def alphabet (map char (range (int \a) (+ 26 (int \a)))))

(def numbers (map char (range (int \0) (+ 10 (int \0)))))

(defn encode [msg]
  (let [encode-map (apply hash-map (concat (interleave numbers numbers)
                                           (interleave alphabet (reverse alphabet))))]
    (->> msg
         clojure.string/lower-case
         (keep encode-map)
         (partition-all 5)
         (map (partial apply str))
         (interpose " ")
         (apply str)
         )))
