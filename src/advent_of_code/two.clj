(ns advent-of-code.two)

(defn package-sides [[ph pw pd]]
  [(* ph pw) (* pw pd) (* ph pd)])

(defn package-area [ p ]
  (+ (* 2 (reduce + (package-sides p)))
     (apply min (package-sides p))))

(defn parse-package [ ps ]
  (map #(Integer/parseInt %) (clojure.string/split ps #"x")))

(defn parse-packages [ pss ]
  (map parse-package (clojure.string/split pss #"\n")))

(defn challenge-1 [ pss ]
  (reduce + (map package-area (parse-packages pss))))

;; Day 2, challenge 2
(challenge-1 (slurp "resources/two.txt"))

(defn package-perrimeters [[ph pw pd]]
  [(+ ph ph pw pw) (+ ph ph pd pd) (+ pw pw pd pd)])

(defn package-volume [p] (apply * p))

(defn ribbon-amount [p]
  (+ (package-volume p) (apply min (package-perrimeters p))))

(defn challenge-2 [ pss ]
  (reduce + (map ribbon-amount (parse-packages pss))))

;; Day 2, challenge 2
(challenge-2 (slurp "resources/two.txt"))