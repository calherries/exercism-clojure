(ns allergies)

(def items
  [:eggs
   :peanuts
   :shellfish
   :strawberries
   :tomatoes
   :chocolate
   :pollen
   :cats])

(defn log2 [n]
  (/ (Math/log n)
     (Math/log 2)))

(defn allergies [n]
  (if (zero? n)
    []
    (let [next-power-of-two (int (log2 n))
          remaining (- n (Math/pow 2 next-power-of-two))
          next-allergy (get items
                            next-power-of-two)]
      (if (some? next-allergy)
        (conj (allergies remaining) next-allergy)
        (allergies remaining)))))

(defn allergic-to? [n item]
  (.contains (allergies n) item))