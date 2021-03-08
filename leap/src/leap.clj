(ns leap)

(defn is-divisible-by
  [a b]
  (= (mod a b) 0))

(defn leap-year? [year] ;; <- argslist goes here
  (and (is-divisible-by year 4)
       (or (not (is-divisible-by year 100))
           (is-divisible-by year 400))))
