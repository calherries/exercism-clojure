(ns space-age)

(defn on-planet
  [orbital-period-earth-years]
  (fn [seconds]
    (/ seconds (* orbital-period-earth-years 31557600.0))))

(def on-earth (on-planet 1.0))
(def on-mercury (on-planet 0.2408467))
(def on-venus (on-planet 0.61519726))
(def on-mars (on-planet 1.8808158))
(def on-jupiter (on-planet 11.862615))
(def on-saturn (on-planet 29.447498))
(def on-uranus (on-planet 84.016846))
(def on-neptune (on-planet 164.79132))
