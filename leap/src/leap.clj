(ns leap)

(defn is-divisible-by
  [a b]
  (zero? (mod a b)))

(defn leap-year? [year]
  (or (is-divisible-by year 400)
      (not (is-divisible-by year 100))
      (is-divisible-by year 4)))
