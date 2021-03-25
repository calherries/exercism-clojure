(ns binary)

(defn to-decimal
  [binary]
  (reduce (fn [acc chr]
            (let [digit-number (Character/digit chr 2)]
              (if (= -1 digit-number)
                (reduced 0)
                (+ (* 2 acc)
                   (Character/digit chr 2)))))
          0
          binary))

;; Character/digit works with single characters, and a radix
(Character/digit \1 2) ; 1
(Character/digit \2 2) ; -1 <- this silently errors
(Character/digit \a 16) ; 10 <- works with hex etc

;; Integer/parseInt works with strings, not with characters
(Integer/parseInt "1") ; 1
(Integer/parseInt "10") ; 10
(Integer/parseInt "a") ; error!
(Integer/parseInt \1) ; error!