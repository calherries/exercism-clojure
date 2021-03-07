(ns robot-name)

(defn random-char []
  (char (+ 65 (rand-int 26))))

(def previous-names
  (atom []))

(defn random-name []
  (apply str
         (concat (repeatedly 2 random-char)
                 (repeatedly 3 #(rand-int 9)))))

(defn new-random-name []
  (let [name (->> (repeatedly 3 random-name)
                  (filter #(not (.contains @previous-names %)))
                  first)]
    (swap! previous-names conj name)
    name))

(defn robot []
  (atom {:name (new-random-name)}))

(defn robot-name [robot]
  (:name @robot))

(defn reset-name [robot]
  (swap! robot assoc :name (new-random-name)))

(comment
  (random-name)
  @previous-names
  (new-random-name)
  (def x (robot))
  (robot-name x)
  (reset-name x))
