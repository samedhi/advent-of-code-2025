#!/usr/bin/env bb

(ns day1b
  (:require [clojure.test :refer [deftest is run-tests]]))

(->> (slurp "day1.txt")
     (clojure.string/split-lines)
     (map #(vector (keyword (subs % 0 1)) (Integer/parseInt (subs % 1))))
     (map (fn [[direction amount]] (* (if (= direction :L) -1 1) amount)))
     (remove zero?)
     (reductions
      (fn [[acc] amount]
        (let [result (+ acc amount)
              final (mod result 100)
              zeroes (+ (quot (abs result) 100)
                        (if (and (pos? acc) ((complement pos?) result)) 1 0))]
          [final zeroes acc amount]))
      [50 0 0 0])
     (map second)
     (apply + )
     println
     )
