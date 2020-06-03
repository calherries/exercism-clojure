(ns reverse-string)

(defn reverse [x]
  (into () x))

(defn reverse-string [s]
  (apply str reverse))
