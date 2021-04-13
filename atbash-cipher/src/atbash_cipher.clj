(ns atbash-cipher)

(defn chars [regex]
  (->> (range 0 256)
       (map char)
       (apply str)
       (re-seq regex)
       (map #(.charAt % 0))))

(def alphabet (chars #"[a-z]"))

(def numbers (chars #"[0-9]"))

(defn encode [msg]
  (let [encode-map (apply hash-map (concat (interleave numbers numbers)
                                           (interleave alphabet (reverse alphabet))))]
    (->> msg
         clojure.string/lower-case
         (keep encode-map)
         (partition-all 5)
         (map (partial apply str))
         (clojure.string/join " "))))
