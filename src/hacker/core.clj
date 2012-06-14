(ns hacker.core
(:use hacker.equipment
      hacker.commands
      hacker.player
      hacker.tasks
      hacker.skills
      hacker.userinput
      hacker.database
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


(defn -main
  "The main loop of the game. Responsible for coordinating everything"
  []
  (do
  (initialize-game)

  (loop
      []

    ;; take input from player
    (main-menu-get-player-input (deref player))
    (recur)))

    (println "DEBUG: END OF LOOP")
    )
