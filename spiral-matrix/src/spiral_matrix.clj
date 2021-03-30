(ns spiral-matrix)

(defn straight-line-lengths [size]
  (let [countdown (range (dec size) 0 -1)]
    (cons (dec size)
          (interleave countdown countdown))))

(defn move-right [[row col]]
  [row (inc col)])

(defn move-left [[row col]]
  [row (dec col)])

(defn move-down [[row col]]
  [(inc row) col])

(defn move-up [[row col]]
  [(dec row) col])

(def circular-moves
  (cycle [move-right move-down move-left move-up]))

(defn all-moves [size]
  (mapcat #(repeat %1 %2)
          (straight-line-lengths size)
          circular-moves))

(defn spiral-ordered-coordinates [size]
  (reductions (fn [current-location move-fn]
                    (move-fn current-location))
                  [0 0]
                  (all-moves size)))

(defn coordinate->value [size]
  (into {} (map vector
                (spiral-ordered-coordinates size)
                (iterate inc 1))))

(defn square [size]
  (partition size
             (map (juxt #(quot % size) #(mod % size))
                  (range (* size size)))))

(defn spiral [size]
  (mapv #(mapv (coordinate->value size) %) (square size)))

(spiral 5)