(ns hacker.core)
(use hacker.commands)
(use hacker.equipment)
(use hacker.player)
(use hacker.tasks)


;;; This is where the main GUI loop runs, drawing functions and data
;;; from the other files.


(defn GUI-task-view
  "Put together the task view seen from the GUI. Current task + time-to-complete; task queue (max 5 items)."
  [player]
  
  )

(defn statgraph
  "Return a little ASCII graph for some statistic. Takes a number and a max-number (full, next level, etc)"
  [current max]
  (println
  ;; display percentage
  (/ current max)
  ;; show a little ASCII bar-graph that represents the percentage
  
  ))

(defn calculate-next-level-points
  "Calculates how many points are needed to reach the next level in a given skill/stat."
  [player attribute]
  
  
  )

(defn GUI-show-player-level
  "Shows a detailed view of the player's level: cute little graph, points to next level, requirements for next level, etc."
  [player]
  (println "Current Level:" (player :hacker-level)
           "Progress to next level:" (statgraph (player :total-points)(calculate-next-level-points player :hacker-level)))
  
  ;; show non-point requirements needed for next level (skills etc)
  
  )


(defn GUI-main-loop
  "The main loop of the game. Responsible for coordinating everything"
  [player]
  ;;;;;;;;;;
  ;; Skills
  ;;;;;;;;;;
  ;; display skills you have
  (list-player-skills player)
  ;;(take input: "skills" brings you to skill-view/training-window)

  ;; show current/queued tasks in an overview
  (GUI-task-view player)

  ;; show energy level
  (println (statgraph (player :focus) 100))
  ;; take input: charge

  ;; show player money
  (println "Money:" (player :money))
  ;; take input: bank

  ;; show equipment
  (list-player-owned-equipment player)
  ;; take input: view or buy more equipment

  ;; show overall player level
  (GUI-show-player-level player)
  )