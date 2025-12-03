#!/usr/bin/env bb

(ns day2a)

(defn subword [word]
  (rest
   (reductions
    (fn [acc c]
      (str acc c))
    ""
    word)))

(defn candidates [word]
  (butlast ;; it includes the word itself
   (for [w (subword word)
         :let [q (quot (count word) (count w))]]
     (apply str (repeat q w)))))

(defn invalid [n]
  (let [s (str n)]
    (contains? (set (candidates s)) s)))

(as-> (slurp "day2.txt") $
  (clojure.string/split $ #",")
  (map #(clojure.string/split % #"-") $)
  (map (fn [[start end]] (vector (parse-long start) (inc (parse-long end)))) $)
  (map (fn [[start end]] (range start (inc end))) $)
  (apply concat $)
  (filter invalid $)
  (apply + $)
  (println $))
