(ns robot-name)

(defn random-char []
  (char (+ 65 (rand-int 26))))

(defn new-random-name []
  (apply str
   (concat (repeatedly 2 random-char)
           (repeatedly 3 #(rand-int 9)))))

(comment
  (int \A)
  (new-random-name))

(defn robot [] ;; <- arglist goes here
  (atom {:name (new-random-name)}))

(defn robot-name [robot]
  (:name @robot))

(defn reset-name [robot]
  (swap! robot assoc :name (new-random-name)))

(comment
  (def x (robot))
  (robot-name x)
  (reset-name x))
