(ns octal)

(defn to-decimal [str-number]
  (let [digits (map #(Character/digit % 8) str-number)]
   (if (.contains digits -1)
     0
     (reduce
      (fn [res digit]
        (+ digit
           (* 8 res)))
      0
      digits))))
