(ns advent-of-code.three)

(defn instruction [i] (get {\^ [0 -1], \v [0 1], \> [1 0], \< [-1 0]} i))

(defn read-instructions [is]
  (loop [pos [0 0] instrs is houses #{}]
    (if (not-empty instrs)
      (recur (map + pos (instruction (first instrs))) (rest instrs) (conj houses pos))
      (conj houses pos))))

(defn challenge-1 [is] (count (read-instructions is)))

;; Day 3 challenge 1
(challenge-1 (slurp "resources/three.txt"))

(defn split-instructions [is]
  (let [santa (take-nth 2 is)
        robo (take-nth 2 (rest is))]
    (clojure.set/union (read-instructions santa) (read-instructions robo))))

(defn challenge-2 [is] (count (split-instructions is)))

;; Day 3 challenge 2
(challenge-2 (slurp "resources/three.txt"))
