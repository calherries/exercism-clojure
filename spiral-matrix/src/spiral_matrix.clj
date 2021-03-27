(ns spiral-matrix
  (:require [vvvvalvalval.supdate.api :as supd :refer [supdate]]))

(defn num-outsides [n]
  (* 4 (- n 1)))

(defn outside-cells [n]
  (let [[top-left top-right bottom-right bottom-left] (partition (- n 1) (range (num-outsides n)))
        outside-cells (->> (concat
                            (map-indexed (fn [idx val]
                                           {:row 0
                                            :col idx
                                            :val val})
                                         top-left)
                            (map-indexed (fn [idx val]
                                           {:row idx
                                            :col (- n 1)
                                            :val val})
                                         top-right)
                            (map-indexed (fn [idx val]
                                           {:row (- n 1)
                                            :col (- n 1 idx)
                                            :val val})
                                         bottom-right)
                            (map-indexed (fn [idx val]
                                           {:row (- n 1 idx)
                                            :col 0
                                            :val val})
                                         bottom-left)))]
    outside-cells))

(defn cells->position-map
  [cells]
  (->> cells
       (map (juxt #(select-keys % [:row :col]) :val))
       (into {})))

(defn spiral-cells [n]
  (case n
    1 '()
    0 '({:row 0
         :col 0
         :val 0})
    (let [smaller-spiral-cells         (spiral-cells (- n 2))
          middled-smaller-spiral-cells (map  (fn [cell]
                                               (-> cell
                                                   (update :row inc)
                                                   (update :col inc)
                                                   (update :val #(+ % (* 4 (- n 1))))))
                                             smaller-spiral-cells)]
      (concat (outside-cells n) middled-smaller-spiral-cells))))

(defn square-positions
  [size]
  (partition size (for [row (range size)
                        col (range size)]
                    {:row row
                     :col col})))

((defn spiral
   [n]
   (supdate
    (square-positions n)
    [[#(inc (get (cells->position-map (spiral-cells n))
                 %))]])))

(comment
  ;; this is how supdate works
  (supdate [[1 2 3] [4 5 6]] [[inc]])
  (mapv #(mapv inc %) [[1 2 3] [4 5 6]]))