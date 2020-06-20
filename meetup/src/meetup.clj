(ns meetup
  (:require [java-time :as t]))

;; Goals: use only the java-time library and make it as functional as possible

(def criteria
  {:first  first
   :second second
   :third  #(nth % 2)
   :fourth #(nth % 3)
   :last   last
   :teenth (fn [days] (first (filter #(<= 13 (t/as % :day-of-month) 19)
                                     days)))})

(defn all-days-in-month [year month]
  (take-while #(= (t/year-month (t/local-date year month))
                  (t/year-month %))
              (t/iterate t/plus (t/local-date year month) (t/days 1))))

(defn meetup [month year weekday schedule]
  (->> (all-days-in-month year month)
       (filter #(= (t/day-of-week weekday) (t/day-of-week %)))
       ((schedule criteria))
       (#(t/as % :year :month-of-year :day-of-month))
       vec))
