(ns sublist)

(defn sublist? [a-list b-list]
  (contains? (set (partition (count a-list) 1 b-list)) a-list))

(defn classify [list1 list2]
  (condp = (compare (count list1)
                    (count list2))
    0 (if (= list1 list2)
        :equal
        :unequal)
    1 (if (sublist? list2 list1)
        :superlist
        :unequal)
    -1 (if (sublist? list1 list2)
         :sublist
         :unequal)))
