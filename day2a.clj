#!/usr/bin/env bb

(ns day2a)

(defn invalid [n]
  (let [s (str n)
        midpoint (-> s count (/ 2))]
    (and (-> s count (mod 2) zero?)
         (= s
            (str (subs s 0 midpoint) (subs s 0 midpoint))))))

(as-> (slurp "day2.txt") $
  (clojure.string/split $ #",")
  (map #(clojure.string/split % #"-") $)
  (map (fn [[start end]] (vector (parse-long start) (inc (parse-long end)))) $)
  (map (fn [[start end]] (range start (inc end))) $)
  (apply concat $)
  (filter invalid $)
  (apply + $)
  (println $))
