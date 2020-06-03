(ns rna-transcription)

(def rna-map 
  {\G \C
   \C \G
   \T \A
   \A \U})

(defn to-rna [dna]
  (apply str (map rna-map dna)))
