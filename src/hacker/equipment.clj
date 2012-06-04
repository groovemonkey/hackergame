(ns hacker.equipment
  (:import java.io.File))

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


(defn set-equipment
  "Set hacker.equipment/equipment to a map of equipment corresponding to each file in dir."
  ([dir]
    (do
      (def equipment (ref {}))
       
      (doseq [f (.listFiles dir)]
        (if (.isDirectory f)    
      ;; run code for each directory (ignores files)      
      (dosync
       (alter equipment merge (load-all-equipment (.getAbsolutePath f))))
      ))))
  
  ([] (set-equipment (File. "data/equipment/"))))

