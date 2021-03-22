(ns scrabble-score)

(def points-raw
  #{[#{"A", "E", "I", "O", "U", "L", "N", "R", "S", "T"} 1]
    [#{"D", "G"} 2]
    [#{"B", "C", "M", "P"} 3]
    [#{"F", "H", "V", "W", "Y"} 4]
    [#{"K"} 5]
    [#{"J", "X"} 8]
    [#{"Q", "Z"} 10]})

(def points-by-letter
   (->> points-raw
        (map (fn [[letters point]]
               (into {}
                     (for [letter letters]
                       [letter point]))))
        (apply merge)))

(defn score-letter [letter]
  (points-by-letter (clojure.string/upper-case letter)))

(defn score-word [word]
  (apply + (map score-letter word)))

(= 1 (scrabble-score/score-letter "a"))
(= 1 (scrabble-score/score-letter "A"))
(= 2 (scrabble-score/score-word "at"))
(= 6 (scrabble-score/score-word "street"))
(= 22 (scrabble-score/score-word "quirky"))
(= 41 (scrabble-score/score-word "OXYPHENBUTAZONE"))