(ns clock)

(defn clock->string [{:keys [hours minutes]}]
  (format "%02d:%02d" hours minutes))

(defn quot-mod [n div]
  [(quot n div) (mod n div)])

(defn clock [hours minutes] ;; <- arglist goes here
  (let [[minutes-quot minutes-mod] (quot-mod minutes 60)
        [hours-quot hours-mod]     (quot-mod (+ hours minutes-quot (if (< minutes 0) -1 0)) 24)]
    {:minutes minutes-mod :hours hours-mod}))

(defn add-time [{:keys [hours minutes]} time] ;; <- arglist goes here
  (clock hours (+ minutes time)))
