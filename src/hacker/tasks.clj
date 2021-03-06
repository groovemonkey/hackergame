(ns hacker.tasks
  (:import java.io.File))

(declare tasks)

(defn load-task [task file]
  (let [thing (read-string (slurp (.getAbsolutePath file)))]
    (conj tasks
          {(keyword (.getName file))
           {:name (keyword (.getName file))
            :title (:title thing)
            :description (:description thing)
            :completion-description (:completion-description thing)
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

(defn set-tasks
  "Set hacker.tasks/tasks to a map of tasks corresponding to each file
  in dir. Defaults to looking in data/tasks/."
  ([dir]
     (do
       (def tasks (ref {}))

       (doseq [file (.listFiles dir)]
       (dosync
        (alter tasks merge (load-all-tasks (.getAbsolutePath file)))))))
  
  ([] (set-tasks (File. "data/tasks/"))))

