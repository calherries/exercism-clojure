(ns raindrops)

(defn is-factor [num quotient]
  (zero? (rem num quotient)))

(defn convert [num]
  (->> [[3 "Pling"]
        [5 "Plang"]
        [7 "Plong"]]
       (filter #(is-factor num (first %))) ;; filtering
       (map second) ;; mapping
       (apply str) ;; reducing
       (#(if (empty? %) ;; replacing
           (str num)
           %))))

;; OR
(defn convert [num]
  (or (reduce (fn [res [quotient output]]
                (if (is-factor num quotient) ;; filtering
                  (str res output) ;; mapping, then reducing
                  res))
              nil
              [[3 "Pling"]
               [5 "Plang"]
               [7 "Plong"]])
      (str num))) ;; replacing

;; OR
(defn convert [num]
  ((reduce (fn [f-acc [quotient output]]
             (if (is-factor num quotient)
               (fn [_] (str (f-acc nil) output)) ;; f (nil)
               f-acc))
           identity
           [[3 "Pling"]
            [5 "Plang"]
            [7 "Plong"]])
   (str num)))
