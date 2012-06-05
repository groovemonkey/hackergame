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


(defn main-menu-get-player-input
  "at the main display, after listing player stats, get player input on what s/he wants to do."
  [player]
  (println "What would you like to do?  Available commands are:
quit
skills
focus
bank
money
equipment

--> ")
  
  (let [input (.toLowerCase (read-line))]
    (println "DEBUG: You said " input)
        (case input
          "quit" nil
          "skills" (skills-menu player)
          "focus" (focus-menu player)
          "bank" (banking-menu player)
          "money" (banking-menu player)
          "equipment" (equipment-menu player)
                    
          )))
