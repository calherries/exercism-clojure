(ns beer-song
  (:require [clojure.string :refer [join]]))

(defmulti verse identity)

(defmethod verse 0 [n]
  "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n")

(defmethod verse 1 [n]
  "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n")

(defmethod verse :default [n]
  (format "%d bottles of beer on the wall, %d bottles of beer.\nTake one down and pass it around, %d %s of beer on the wall.\n" n n (dec n) (if (= n 2) "bottle" "bottles")))

(defn sing
  ([vs] (sing vs 0))
  ([vs ve]
   (->> (range vs (dec ve) -1)
        (map verse)
        (join "\n"))))

(comment (verse 2))
