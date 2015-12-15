(ns advent-of-code.one)

(defn instruction [i] (get {\( 1, \) -1} i))

(defn challenge-1 "Given a list of instructions, determine where Santa will end up"
  [i] (reduce #(+ (instruction %2) %1) 0 i))

;; Day 1, challenge 1:
(challenge-1 (slurp "resources/one.txt"))

(defn challenge-2 "Given a list of instructions, determine when Santa enters the basement"
  [i] (let [reducer (fn [acc [index i]]
                      (let [next (+ (instruction i) acc)]
                        (if (> 0 next) (reduced index) next)))]
        (reduce reducer 0 (map #(vector (inc %1) %2) (range) i))))

;; Day 1, challenge 2:
(challenge-2 (slurp "resources/one.txt"))