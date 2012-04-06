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


(defn old-list-player-owned-equipment
  "display a list of user-owned equipment."
  [player]
  (println "\n\nEquipment You Own:\n"
           ;;map a function that displays each item in the type-list,
           ;;along with the type's name.
           (map #(println %":") (player :equipment-owned))))
(defn list-player-owned-equipment
  "display a list of user-owned equipment.  Use map Destructuring."
  [{:keys [equipment-owned] :as player-map}]
  (do
    (println "\n\nEquipment You Own:\n")
           (doseq [type equipment-owned]
             (println type ":" (:values type)))))




(defn list-player-skills
  "display a list of user skills."
  [player]
  (println "\n\nYour Skills:\n"
           ;;map a function that displays each item in the type-list,
           ;;along with the type's name.  TODO: make pretty
           (map #(println %":") (player :skills))))






(def view-task-queue
  "View the current task queue for player."
  [player]

  )


(def remove-task-from-queue
  "Remove a task from the player's queue."
  [task player]

  )

(def list-inner-map
  "used in the view-task-details function: takes a key and returns a nice little string that shows what that map contains."
  [object attribute]
  (doseq [thing (object attribute)]
                            (println thing))
  )

(def view-task-details
  "Pretty-print the details for a specific task."
  [task]
  (println (task :title)
           "Description:" (task :description)
           "Cost:" (task :money-cost)
           "Focus:" (task :focus-cost)
           "Hack-Points:" (task :hack-points)

           "\nRewards:\n"
           "Cash:" (task :payout-cash)
           "Skillpoints:" (list-inner-map task :payout-skillpoints)

           "\n\nRequirements:\n" (list-inner-map task :skills-required)
           "\n\nEquipment Required:\n"
           (list-inner map task :equipment-required)
   )
)


(def list-available-tasks
  "Returns tasks that are available to the player. Also shows greyed-out tasks if they are within 1 hacker/skill-level of being available."
  [player]
  (doseq [task tasks]
    ;; if the task's level is less than the player's level +1,
    ;; display it.  We want to see what's coming up!
    (if (<= (tasks :task-level) (+ 1 (player :hacker-level)))
    (view-task-details task)))
  )


(def add-task-to-queue
  "Add a task to the player's queue. Performs a check (same as view-available-tasks) to verify that the task can actually be added to the queue."
  [task player]

  )

(def calculate-task-duration
  "Calculate duration for a task. This should work for both queued tasks (calculate at current skill/focus/etc. levels) and for an active task (calculate remaining time, etc)"
  [task]
  
  )


(def task-completed
  "Run the actions needed when a task completes. Run the 'completion description' dialogue, remove the task from the front of the queue, start the next task (or do that somewhere else, in a higher-level 'controller?'), distribute the cash and exp payout, etc."
  [task]
  
  )
