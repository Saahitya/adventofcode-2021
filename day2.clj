(require '[clojure.string :as str])


(defn depth-sign [el] (let [[dir value] el]
                        (case dir
                          "up" (- value)
                          "down" value)))

(defn final-depth [l]
  (reduce + (map depth-sign (filter #(contains? #{"up" "down"} (first %1)) l))))

(defn final-horizontal-pos [l]
  (reduce + (map second (filter #(= "forward" (first %1)) l))))

(def sub-instrs (str/split-lines (slurp "in2.in")))

(defn sub-instr-parse [s]
  (let [[dir value] (str/split s #" ")]
    (list dir (Integer/parseInt value))))

(def sub-instrs (map sub-instr-parse sub-instr-list))

;; part 1
;; (* (final-depth sub-instrs) (final-horizontal-pos sub-instrs))

(defn prcs-sub-instr
  "Reads submarine instructions and outputs the product of final depth and horizontal position."
  [l x y aim]
  (if (empty? l)
    (* x y)
    (let [[dir value] (first l)]
      (case dir
        "up"      (prcs-sub-instr (next l) x y (- aim value))
        "down"    (prcs-sub-instr (next l) x y (+ aim value))
        "forward" (prcs-sub-instr (next l) (+ x value) (+ y (* aim value)) aim)))))

;; part 2
(prcs-sub-instr sub-instrs 0 0 0)
