(require '[clojure.string :as str])

(defn increased? [a b]
  (if (< a b)
    1
    0))

(defn depth-measure-increment [l]
  (if (< (count l) 2)
    0
    (+ (depth-measure-increment (rest l)) (increased? (first l) (second l)))))

(def report (map #(Integer/parseInt %) (str/split-lines (slurp "in1.in"))))

(defn three-num-sliding-window [l]
  (map + l (next l) (nnext l)))

;; part 1
;; (depth-measurement-increment report)

;; part 2
(depth-measure-increment (three-num-sliding-window report))
