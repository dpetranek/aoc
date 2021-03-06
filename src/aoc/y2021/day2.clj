(ns aoc.y2021.day2
  (:require [aoc.util :as ut]
            [clojure.string :as str]))

(defn raw->edn
  [strings]
  (->> strings
       (map (fn [s] (str/split s #" ")))
       (map (fn [[cmd dist]] [cmd (Integer/parseInt dist)]))))

(defn navigate [[progress depth] [cmd dist]]
  (case cmd
    "forward"  [(+ progress dist) depth]
    "backward" [(- progress dist) depth]
    "up"       [progress (- depth dist)]
    "down"     [progress (+ depth dist)]))

(comment
  (def input (raw->edn (ut/load-input "2_1.txt")))

  ;; Part 1

  (let [[depth progress] (reduce navigate [0 0] input)]
    (* depth progress))
  ;; 2120749


  ,)

(defn navigate-correctly
  [{:keys [progress depth aim] :as pos} [cmd dist]]
  (case cmd
    "forward" (-> pos
                  (assoc :progress (+ progress dist))
                  (assoc :depth (+ depth (* aim dist))))
    "up"      (assoc pos :aim (- aim dist))
    "down"    (assoc pos :aim (+ aim dist))))

(comment
  input

  ;; Part 2

  (let [{:keys [depth progress]} (reduce navigate-correctly {:progress 0 :depth 0 :aim 0} input)]
    (* depth progress))
  ;; 2138382217


  ,)
