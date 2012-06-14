(ns hacker.userinput
  (:use hacker.commands
  ))


(defn skills-menu
  ""
  [player]


  )


(defn focus-menu
  ""
  [player]


  )


(defn banking-menu
  ""
  [player]


  )


(defn equipment-buy-menu
  "chosen at the equipment-menu after the player chooses 'buy'"
  [player]
  (do
    (println "\nHere's equipment that's available to you right now:")
    ;; FIXME: This breaks everything
    ;;(hacker.commands/list-available-equipment)

    (println "Type 'buy <item> to buy an item.")
    (comment (let [input (.toLowerCase (read-line))]
      ;;TODO: split the input into a vector so you have command/item

               (case input
                 ;; FIXME: this also breaks everything
        "buy" (buy-equipment input))))))


(defn equipment-menu
  "CENTRAL FUNCTION: view/buy equipment brings you to the proper equipment screen (list-available-equipment) or (list-player-owned-equipment player) -- somehow also incorporate (view-equipment-item-details) and (buy-equipment item) functions."
  [player]
  (do
    (println "\n\nWelcome to the equipment menu.  Available commands:
buy - see available equipment and buy it
view - view your own equipment
")
    (let [input (.toLowerCase (read-line))]
      (case input
        "buy" (equipment-buy-menu player)
        "view" (list-player-owned-equipment player)

        )
    )))


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
  



(defn main-menu-get-player-input
  "at the main display, after listing player stats, get player input on what s/he wants to do."
  [player]
  (println "What would you like to do?  Available commands are:
player - view player stats
quit - quit the game
skills - view your skills
focus - debug: do stuff with focus
bank - check your finances
money - check your finances
equipment - view and buy equipment")

(print "--> ")
  
  (let [input (.toLowerCase (read-line))]
    (println "DEBUG: You said " input)
        (case input
          "quit" nil
          "skills" (skills-menu player)
          "focus" (focus-menu player)
          "bank" (banking-menu player)
          "money" (banking-menu player)
          "equipment" (equipment-menu player)
          "player" (displaystats player)
                    
          )))
