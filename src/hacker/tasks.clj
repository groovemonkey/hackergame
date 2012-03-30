(ns hacker.tasks)
(declare tasks)

(defn load-task [task file]
  (let [thing (read-string (slurp (.getAbsolutePath file)))]
    (conj tasks
          {(keyword (.getName file))
           {:name (keyword (.getName file))
            :description (:description thing)
            :task-level (:task-level thing)
            :money-cost (:money-cost thing)
            :focus-cost (:focus-cost thing)
            :hack-points (:hack-points thing)
            :payout-cash (:payout-cash thing)
            :payout-skillpoints (:payout-skillpoints thing)
            :payout-bonus (:payout-bonus thing)
            :skills-required (:skills-required thing)
            :equipment-required (:equipment-required thing)}})))
            

(defn load-all-tasks [dir]
  "Given a dir, return a map with an entry corresponding to each file
in it. Files should be maps containing task data."
  (reduce load-task {} (.listFiles (java.io.File. dir))))

;; TODO: load multiple items from subdirectories of /data/tasks
(defn set-tasks
  "Set hacker.tasks/tasks to a map of tasks corresponding to each file
  in dir. Defaults to looking in data/tasks/."
  ([dir]
     (def tasks (load-all-tasks dir)))
  ([] (set-equipment "data/tasks/")))
