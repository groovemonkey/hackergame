(ns hacker.commands
  (:use [hacker equipment player tasks skills])
  )



;; TODO make permanent (mutable)                                              
(defn player-toplevel-attr-change
  "Add (positive integer) or remove (negative integer) numeric attributes from the player map.  This is for things like total-points, hacker-level, money, focus, etc"
  [attr amount]
  (assoc-in player [attr] (+ (player attr) amount))
  )

;; TODO: make this an actual validation -- right now everything that has some kind of requirement will fail.
(defn buyable?
  "A validation function that checks equipment stats against player stats, to make sure all the requirements for that item are met. Called by the buy-equipment function. We are checking skills, hideout, and equipment"
  [item]
  (and
       (empty? (item :skills-required))
       (empty? (item :hideout-required))
       (empty? (item :equipment-required)))

  )


;; TODO make this permanent (currently immutable)
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
  (doseq [item hacker.equipment/equipment]
    ;; if the item's level is less than the player's level +1,
    ;; display it.  We want to see what's coming up!
    (if (<= (item :level) (+ 1 (player :hacker-level)))
    (view-equipment-item-details item))))


(defn list-player-owned-equipment
  "display a list of user-owned equipment.  Use map Destructuring."
  [{:keys [equipment-owned] :as player-map}]
  (do
    (println "\n\nEquipment You Own:\n")
           (doseq [type equipment-owned]
             (println (first type) ":" (rest type)))))


(defn list-player-skills
  "Display a list of user skills.  Use map Destructuring."
  [player-map]
  (do
    (println "\n\nYour Skills:\n")
           (doseq [skill (get-in player-map [:skills])]
             (println "\n"(key skill) ":" (val skill)
                      "\nProficiency:" (get-in hacker.skills/skills [(key skill) (keyword (val skill)) :level-name])
                      "\nDescription:" (get-in hacker.skills/skills [(key skill) (val skill) :description])

;;(get-in hacker.skills/skills [(first skill) (keyword (rest skill))])
                      ))))

;; I want name, level number, level name, description

;; get a player's skill in (for example) programming
;;  (get-in (deref player) [:skills :programming])

;; (get-in hacker.skills/skills [:anonymity :level-0])
;; this has :level-name :description and :skill-points-required



(defn view-task-queue
  "View the current task queue for player."
  [player]

  )


(defn remove-task-from-queue
  "Remove a task from the player's queue."
  [task player]

  )


(defn list-inner-map
  "used in the view-task-details function: takes a key and returns a nice little string that shows what that map contains."
  [object attribute]
  (doseq [thing (object attribute)]
                            (println thing))
  )


(defn view-task-details
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
           (list-inner-map task :equipment-required)
   )
)


(defn list-available-tasks
  "Returns tasks that are available to the player. Also shows greyed-out tasks if they are within 1 hacker/skill-level of being available."
  [player]
  (doseq [task tasks]
    ;; if the task's level is less than the player's level +1,
    ;; display it.  We want to see what's coming up!
    (if (<= (tasks :task-level) (+ 1 (player :hacker-level)))
    (view-task-details task)))
  )


(defn add-task-to-queue
  "Add a task to the player's queue. Performs a check (same as view-available-tasks) to verify that the task can actually be added to the queue."
  [task player]

  )

(defn calculate-task-duration
  "Calculate duration for a task. This should work for both queued tasks (calculate at current skill/focus/etc. levels) and for an active task (calculate remaining time, etc)"
  [task]
  
  )


(defn task-completed
  "Run the actions needed when a task completes. Run the 'completion description' dialogue, remove the task from the front of the queue, start the next task (or do that somewhere else, in a higher-level 'controller?'), distribute the cash and exp payout, etc."
  [task]
  
  )
