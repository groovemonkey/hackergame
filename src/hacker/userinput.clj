(ns hacker.userinput)


(def skills-menu
  ""
  []


  )


(def focus-menu
  ""
  []


  )


(def banking-menu
  ""
  []


  )


(def equipment-menu
  "view/buy equipment brings you to the proper equipment screen (list-available-equipment) or (list-player-owned-equipment player) -- somehow also incorporate (view-equipment-item-details) and (buy-equipment item) functions."
  []

  )


(defn main-menu-get-player-input
  "at the main display, after listing player stats, get player input on what s/he wants to do."
  []
  (println "What would you like to do?")
  (let [input (.toLowerCase (read-line))]
    (println "DEBUG: You said " input)
        (case input
          "quit" nil
          "skills" (skills-menu)
          "focus" (focus-menu)
          "bank" (banking-menu)
          "money" (banking-menu)
          "equipment" (equipment-menu)
                    
          )))
