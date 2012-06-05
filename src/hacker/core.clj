(ns hacker.core
(:use hacker.equipment
      hacker.commands
      hacker.player
      hacker.tasks
      hacker.skills
      hacker.userinput
      ))

;;; This is where the main GUI/game loop stuff runs, drawing functions
;;; and data from the other source files.
(defn initialize-game
  "Creates player object, reads game data into memory"
  []
  (print "Initializing...")
  (set-player)
  (set-equipment)
  (set-skills)
  (set-tasks)
  (print "Done!\n\n")
  )

(defn GUI-task-view
  "Put together the task view seen from the GUI. Current task + time-to-complete; task queue (max 5 items)."
  [player]
  
  )

(defn statgraph
  "Return a little ASCII graph for some statistic. Takes a number and a max-number (full, next level, etc)"
  [current max]
  (println
  ;; display percentage
   (if (> current 0)
     (println (/ current max)"%")
     (println "Zero Percent -- You got Nuthin'!"))
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
           "\nProgress to next level:" (statgraph (player :total-points)(calculate-next-level-points player :hacker-level)))
  
  ;; show non-point requirements needed for next level (skills etc)
  
  )


  (defn displaystats
    "Display User Statistics"
    [player]
    (println
    "\nYour Stats:\n"
    "\nName:" (player :name)
    "\nHacker Alias:" (player :hacker-alias)
    "\nBackground:" (player :background-story)
             ;; show overall player level
             (GUI-show-player-level player)
             "\nTotal Points:"(player :total-points)
             "\nMoney:"(player :money)
             "\n\n"(list-player-owned-equipment player)
             "\n\n"(list-player-skills player)
                      
             ;; show current/queued tasks in an overview
             (GUI-task-view player)

             ;; show energy level
             "\nYour Focus:" (statgraph (player :focus) 100)"%"
             "\nFocus Recharge Rate:"(player :focus-recharge-rate)))


(defn player-quits
  "returns true if player selects 'quit' in the player-input loop."
  []
  (println "\nDo you want to quit?\n")
  (if (= (read-line) "y")
    '(true)
    nil
  ))


(defn -main
  "The main loop of the game. Responsible for coordinating everything"
  []
  (do
  (initialize-game)

  (loop
      []
    (displaystats (deref player))

    ;; take input from player
    (main-menu-get-player-input (deref player))
    (if (player-quits)
      nil
    (recur)))

    (println "DEBUG: END OF LOOP")
    ))
