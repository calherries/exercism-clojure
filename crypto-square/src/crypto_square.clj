(ns crypto-square
  (:require [clojure.string :as str]))

(defn normalize-plaintext [s]
  (apply str (re-seq #"[a-z0-9]" (str/lower-case s))))

(defn square-size [s]
  (int (Math/ceil (Math/sqrt (count s)))))

(defn plaintext-segments [s]
  (->> s
       normalize-plaintext
       (#(partition-all (square-size %) %))
       (mapv (partial apply str))))

(defn transpose [matrix]
  (if (empty? (first matrix))
    '()
    (cons (map first matrix)
          (transpose (map rest matrix)))))

(defn ciphertext [s]
  (->> s
       plaintext-segments
       transpose
       (map (partial apply str))
       str/join))

(defn normalize [s]
  (let [size (square-size s)
        rows (int (Math/ceil (/ (count s) size)))
        chars-short (- (* size rows) (count s))
        full-rows (- rows chars-short)]
    (loop [acc []
           idx 0
           remaining-string s]
      (if (empty? remaining-string)
        acc
        (let [pad? (<= full-rows idx)
              chunk-size (if pad?
                           (dec size)
                           size)
              chunk (apply str (take chunk-size remaining-string))]
          (recur (conj acc chunk)
                 (inc idx)
                 (drop chunk-size remaining-string)))))))

(defn normalize-ciphertext [s]
  (->> s ciphertext normalize (str/join " " ,)))
