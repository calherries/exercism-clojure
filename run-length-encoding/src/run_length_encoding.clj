(ns run-length-encoding)

(defn run-length-encode
  "encodes a string with run-length-encoding"
  [plain-text]
  (->> plain-text
       (partition-by identity)
       (mapcat (juxt count first))
       (remove #{1})
       (apply str)))

(defn expand-char 
  [n char] 
  (take n (repeat char)))

(def get-digit (comp read-string str))

(defn run-length-decode
  [cipher-text]
  (->> cipher-text
       (re-seq #"\d*\D")
       (mapcat #(if (= 1 (count %))
                  %
                  (repeat (read-string (apply str (drop-last %)))
                          (last %))))
       (apply str)))
