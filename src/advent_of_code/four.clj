(ns advent-of-code.four
  (:import java.security.MessageDigest
           java.math.BigInteger))

;; --- Day 4: The Ideal Stocking Stuffer ---
;;
;; Santa needs help mining some AdventCoins (very similar to bitcoins) to use as gifts for all the economically
;; forward-thinking little girls and boys.
;;
;; To do this, he needs to find MD5 hashes which, in hexadecimal, start with at least five zeroes. The input to the MD5
;; hash is some secret key (your puzzle input, given below) followed by a number in decimal. To mine AdventCoins, you
;; must find Santa the lowest positive number (no leading zeroes: 1, 2, 3, ...) that produces such a hash.
;;
;; For example:
;;
;; If your secret key is abcdef, the answer is 609043, because the MD5 hash of abcdef609043 starts with five zeroes
;; (000001dbbfa...), and it is the lowest such number to do so.
;; If your secret key is pqrstuv, the lowest number it combines with to make an MD5 hash starting with five zeroes is
;; 1048970; that is, the MD5 hash of pqrstuv1048970 looks like 000006136ef....

(def puzzle-key "bgvyzdsv")

(defn md5 [s]
  (let [algorithm (MessageDigest/getInstance "MD5")
        size (* 2 (.getDigestLength algorithm))
        raw (.digest algorithm (.getBytes s))
        sig (.toString (BigInteger. 1 raw) 16)
        padding (apply str (repeat (- size (count sig)) "0"))]
    (str padding sig)))

(defn list->str [lc] (apply str lc))

(defn advent-coin? [secret depth i]
  (when (= (list->str (take depth (repeat "0")))
           (list->str (take depth (md5 (str secret i)))))
    i))

(defn advent-coins [ key depth ]
  (filter (partial advent-coin? key depth) (range)))

;; Day 4 part 1 result:
(first (advent-coins puzzle-key 5))

;; --- Part Two ---
;;
;; Now find one that starts with six zeroes.

;; Day 4 part 2 result:
(first (advent-coins puzzle-key 6))