(ns complex-numbers
  (:require [clojure.math.numeric-tower :as math]))

(defn real [[a b]] a)

(defn imaginary [[a b]] b)

(defn square [x y] (* x y))

(defn abs [[a b]] ;; <- arglist goes here
  (math/sqrt (+ (square a) (square b))))

(defn conjugate [n] ;; <- arglist goes here
  (square (abs n)))

(defn add [[a b] [c d]] ;; <- arglist goes here
  [(+ a c) (+ b d)])

(defn sub [[a b] [c d]] ;; <- arglist goes here
  [(- a c) (- b d)])

(defn mul [[a b] [c d]]
  [(- (* a c) (* b d)) (+ (* b c) (* a d))])

(defn div [[a b] [c d]]
  [(- (* a c) (* b d)) (+ (* b c) (* a d))])
