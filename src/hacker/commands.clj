(ns hacker.commands
  (:use [hacker equipment player tasks])
  )



; TODO make permanent (mutable)                                              
(defn player-toplevel-attr-change
  "Add (positive integer) or remove (negative integer) numeric attributes from the player map.  This is for things like total-points, hacker-level, money, focus, etc"
  [attr amount]
  (assoc-in player [attr] (+ (player attr) amount))
  )

TODO: make this an actual validation -- right now everything that has some kind of requirement will fail.
(defn buyable?
  "A validation function that checks equipment stats against player stats, to make sure all the requirements for that item are met. Called by the buy-equipment function. We are checking skills, hideout, and equipment"
  [item]
  (and
       (empty? (item :skills-required))
       (empty? (item :hideout-required))
       (empty? (item :equipment-required)))

  )


;TODO make this permanent (currently immutable)
(defn buy-equipment
  "Add a piece of equipment to player's equipment map.  Syntax: (buy-equipment <equipment-symbol>).  Must be called in a transaction."
  [item]
  (if (buyable? item)
    (do
    (assoc-in player [:equipment-owned (:type item)] item)
    (player-toplevel-attr-change :money (- (item :price))))))


(defn view-equipment-item-details
  "Pretty-print the player-visible stats of a piece of equipment.  Intended to be used in places where we need to have an item's info shown somewhere, for example in list-available-equipment (below)"
  [item]
  
  (println "Item:" (item :name) "(Level" (item :level)(item :type)")"
           "\n\nPrice:" (item :price)
           "\nDescription:" (item :description))
  
      ;; Whether the item adds compute or defense speed, display it.
  (if (= (item :type) (or :computer :interface :augmentation))
    (println "Compute Speed:" (item :compute-speed))
    (println "Defense Value:" (item :defense-value)))

  (println "Requirements:\n###########\n
Hideout: "(if (empty? (item :hideout-required)) "None\n" (:hideout-required))
"Skills: "(if (empty? (item :skills-required)) "None\n" (:skills-required))
"Equipment: "(if (empty? (item :equipment-required)) "None\n" (:equipment-required))))


(defn list-available-equipment
  "list equipment based on equipment level (+1 level, greyed out).  Show selectable equipment based on required level, $, Focus, and Skills."
  []
  (doseq [item equipment/equipment]
    ;; if the item's level is less than the player's level +1,
    ;; display it.  We want to see what's coming up!
    (if (<= (item :level) (+ 1 (player :hacker-level)))
    (view-equipment-item-details item))))


;commands we still need for the prototype:

;inventory-menu: view own equipment
;---view specific class of equipment (comp, augment, sec, etc)
;---view specific item

;view tasks (current queue)
;---remove tasks from queue
;new task (available tasks)
;choose task (queue an available task)

