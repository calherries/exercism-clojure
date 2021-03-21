(ns gigasecond)

(defn days->seconds
  [days]
  (* days (* 60 60 24)))

(defn seconds->days
  [seconds]
  (/ seconds (* 60 60 24)))

(= 2 (seconds->days (days->seconds 2)))


(= 366 (year->days 1))
(= 731 (year->days 2))

(defn is-divisible [n divisor]
  (= 0 (mod n divisor)))

(defn is-leap-year
  [year]
  (cond (is-divisible year 400) true
        (is-divisible year 100) false
        (is-divisible year 4) true
        :else false))

(defn number-of-leap-years
  [year]
  (count (filter is-leap-year (range year))))

(defn days->year-and-day
  [days]
  (loop [days-left days
         year 0]
    (let [days-in-year (if (is-leap-year year)
                         366
                         365)]
      (if (< days-left days-in-year)
        {:year year
         :day (+ 1 days-left)}
        (recur (- days-left days-in-year) (inc year))))))

(days->year-and-day 365)

(defn year->days
  [years]
  (let [leap-years (number-of-leap-years years)]
    (+  (* years 365)
        leap-years)))

(year-days 0)

(= 1 (number-of-leap-years 1))
(= 1 (number-of-leap-years 3))

(= true (is-leap-year 2020))
(= true (is-leap-year 2000))
(= false (is-leap-year 2100))

(defn month->days
  [year month]
  ({1 31
    2 (if (is-leap-year year)
        28
        27)
    3 31
    4 30
    5 31
    6 30
    7 31
    8 31
    9 30
    10 31
    11 30
    12 31} month))

(def year 2020)
(def day-of-year 2)

(defn year-and-day->date
  [year day-of-year]
  (let [[month last-of-last-month-day-of-year]
        (->> (range 1 12)
             (map #(month->days year %))
             (reductions +)
             (take-while #(< % day-of-year))
             (map vector (range 2 13))
             last
             (#(or % [1 0])))
        day-of-month (- day-of-year last-of-last-month-day-of-year)]
    {:year year
     :month month
     :day day-of-month}))

(= {:year 2020
    :month 12
    :day 31}
   (year-and-day->date 2020 365))

(defn months->days
  [year month]
  (apply + (map #(month->days year %) (range 1 month))))

(= 31 (months->days 0 2))
(= 31 (months->days 2020 2))
(= 28 (month->days 2020 2))
(= 0 (year->days 0))


(defn date->seconds
  [year month day]
  (days->seconds (- (+ (year->days year)
                       (months->days year month)
                       day)
                    1)))

(= (days->seconds 366) (date->seconds 1 1 1))

(date->seconds 2011 4 24)
(= (days->seconds 30) (date->seconds 0 1 31))

(- (year->days 399)
   (year->days 400))

(def days-every-400-years (year->days 400))

(def average-days-each-year (/ days-every-400-years 400.0))

(every? = (map vector (range 0 3000) (map days->year (map year->days (range 3000)))))

(days->year (year->days 2043))

(defn days->year
  [days]
  (int (quot days average-days-each-year)))

(defn seconds->year-and-day
  [seconds]
  (let [days (seconds->days seconds)
        {:keys [year day]} (days->year-and-day days)]
    {:year year
     :day day}))

(seconds->year-and-day (date->seconds 2011 4 24))

(defn seconds->date
  [seconds]
  (let [{:keys [year day]} (seconds->year-and-day seconds)]
    (year-and-day->date year day)))

(date->seconds 0 1 25)
(seconds->year-and-day 1)
(seconds->date 1)
(seconds->date (date->seconds 0 1 25))
(seconds->date (date->seconds 2011 4 25))
(seconds->date (date->seconds 2043 1 1))
(date->seconds year month day)
(date->seconds 2043 1 1)
(seconds->date 1)

(def giga 1000000000)

(def a 1)
(def a 2)
a
years
month
day
(meta year)
(resolve 'year)
(def years 2011)
(def month 4)
(def day 25)

(def s 64470822400)
(seconds->date 64470822400)

(defn from
  [year month day]
  (seconds->date (+ giga (date->seconds years month day))))

(every? = (map vector (range 0 3000) (map days->year (map year->days (range 3000)))))
(remove #(= 1 (:day %)) (map seconds->year-and-day (map date->seconds (range 100) (repeat 1) (repeat 1))))
(quot 366 (* average-days-each-year 1))
(/  (seconds->days (date->seconds 1 1 1))
    average-days-each-year)
(seconds->year-and-day (date->seconds 2042 12 31))
(seconds->date (date->seconds 2042 12 32))
(seconds->date (date->seconds 2042 12 32))

(= [2043 1 1] (gigasecond/from 2011 4 25))
