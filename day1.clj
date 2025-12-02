#!/usr/bin/env bb

(ns day1
  (:require [clojure.test :refer [deftest is run-tests]]))

(->> (slurp "day1.txt")
     (clojure.string/split-lines)
     (map #(vector (keyword (subs % 0 1)) (Integer/parseInt (subs % 1))))
     (reductions
      (fn [acc [direction amount]]
        (if (= direction :L)
          (- acc amount)
          (+ acc amount)))
      50)
     (map #(mod % 100))
     (filter zero?)
     count
     println)
