(ns nucleotide-count)

(defn nucleotide-counts [strand] ;; <- Arglist goes here
  (->> strand
       frequencies
       (merge {\A 0 \G 0 \C 0 \T 0})))

(defn count-of-nucleotide-in-strand [nucleotide strand] ;; <- Arglist goes here
  {:pre [(#{\G \A \C \T} nucleotide)]}
  (get (nucleotide-counts strand) nucleotide))
