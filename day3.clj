(require '[clojure.string :as str])


(def diag-report (str/split-lines (slurp "in3.in")))

(defn get-col-n [n mat]
  (map #(get %1 n) mat))

(defn most-frequent-n [n items]
  (->> items
       frequencies
       (sort-by val)
       reverse
       (take n)
       (map first)))

(defn bin-digit-flip [bin-digit]
  (if (= bin-digit 1)
    0
    1))

(def trans-diag-report (map #(get-col-n %1 diag-report) (range 12)))

(def bin-gamma-rate (map #(Character/digit %1 10)  (map first (map #(most-frequent-n 1 %1) trans-diag-report))))

(def bin-epsilon-rate (map bin-digit-flip bin-gamma-rate))

(def bin-gamma-rate (str/join "" bin-gamma-rate))
(def bin-epsilon-rate (str/join "" bin-epsilon-rate))


;; part 1
;; (def power-consumption (Integer/parseInt bin-gamma-rate 2) (Integer/parseInt bin-epsilon-rate 2))


