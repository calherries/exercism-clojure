(ns phone-number)

(defn number [num-string]
  (let [digits     (re-seq #"\d" num-string)
        num-digits (count digits)]
    (if (or (= num-digits 10) (and (= num-digits 11) (= (first digits) "1")))
      (->> (take-last 10 digits)
           (apply str))
      "0000000000"))
  )

(defn area-code [num-string]
  (subs (number num-string) 0 3))

(defn pretty-print [num-string]
  (let [n (number num-string)]
    (str "(" (area-code num-string) ") " (subs n 3 6) "-" (subs n 6))))
