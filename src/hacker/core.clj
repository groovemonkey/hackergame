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

(defn main-menu-get-player-input
  "at the main display, after listing player stats, get player input on what s/he wants to do."
  [player]
  ;; what would you like to do?

  ;;(take input: "skills" brings you to skill-view/training-window)

  ;; "charge focus" brings you to the focus-buying screen

  ;; "bank" or "money" brings you to the banking screen

  ;; "view/buy equipment" brings you to the proper equipment screen
  ;; (list-available-equipment) or (list-player-owned-equipment
  ;; player) -- somehow also incorporate (view-equipment-item-details)
  ;; and (buy-equipment item)
  
  
  
  )



  (defn displaystats
    "Display User Statistics"
    [player]
    (println "Your Stats:\n"
             ;; show overall player level
             (GUI-show-player-level player)
             "\nTotal Points:"(player :total-points)
             "\nMoney:"(player :money)
             "\n\n"(list-player-owned-equipment player)
             "\n\n"(list-player-skills player)
                      
             ;; show current/queued tasks in an overview
             (GUI-task-view player)

             ;; show energy level
             (statgraph (player :focus) 100)
             "\nFocus Recharge Rate:"(player :focus-recharge-rate)

  ))



(defn main-loop
  "The main loop of the game. Responsible for coordinating everything"
  [player]
  (displaystats player)
  
  ;; take input from player
  (main-menu-get-player-input)

  )