(ns hacker.equipment)
(declare equipment)

(defn load-equipment [equipment file]
  (let [thing (read-string (slurp (.getAbsolutePath file)))]
    (conj equipment
          {(keyword (.getName file))
           {:name (keyword (.getName file))
            :description (:description thing)
            :type (:type thing)
            :level (:level thing)
            :price (:price thing)
            :compute-speed (:compute-speed thing)
            :defense-value (:defense-value thing)
            :hideout-required (:hideout-required thing)
            :player-level-required (:player-level-required thing)
            :skills-required (:skills-required thing)
            :equipment-required (:equipment-required thing)
            :general-bonus (:general-bonus thing)
            :task-bonuses (:task-bonuses thing)}})))


(defn load-all-equipment [dir]
  "Given a dir, return a map with an entry corresponding to each file
in it. Files should be maps containing equipment data."
  (reduce load-equipment {} (.listFiles (java.io.File. dir))))

;; TODO: load multiple items from subdirectories of /data/equipment
(defn set-equipment
  "Set hacker.equipment/equipment to a map of equipment corresponding to each file
  in dir. This function should be used only once at mire startup, so
  having a def inside the function body should be OK. Defaults to
  looking in data/equipment/."
  ([dir]
     (def equipment (load-all-equipment dir)))
  ([] (set-equipment "data/equipment/")))
