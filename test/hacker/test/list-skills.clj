(def player (ref
  {:background-story "You come from a long line of male prostitutes, once an honored family but now fallen from grace. The first in six generations to learn how to read, you've decided to give up the life of the night, and turn your attention to computers. Who knows what fame and fortune -- and discoveries -- might await?  Well, I do.  Because I'm writing this game.",
   :money 100,
   :skills {:programming :0,
            :cracking :0,
            :evasion :0,
            :underworld-connections :0,
            :anonymity :0,
            :network-tech :0,
            :social-engineering :0},
   :focus-recharge-rate 10,
   :total-points 0,
   :name "Mr. Anderson",
   :tasks {:current "none",
           :queued []},
   :focus 10,
   :hacker-alias "Neo",
   :hacker-level 0,
   :equipment-owned {:computer [],
                     :interface [],
                     :augmentation [],
                     :hideout [],
                     :security-network [],
                     :security-physical []}}
))


(defn test-one
  [{:keys [skills] :as player-map}]                                  
(doseq [skill skills] (get-in player-map [:skills (keyword skill)])))

(defn test-two
  [player-map]                                  
  (doseq [skill (get-in player-map [:skills])]
    (println "the chosen skill is" skill)))


;;; WINNER (once I changed the player map to use keys instead of ints
;;; as the value of a skill-level.
(defn test-three
  [player-map]                                  
  (doseq [skill (get-in player-map [:skills])]
    (println "the chosen skill KEY is\n"(key skill)
             "\n, and the value is\n" (val skill))
    ))
  
(defn test-four
  [{:keys [skills] :as player-map}]                                  
  (doseq [skill skills]
    (get-in player-map [:skills (keyword skill)])))


(println "\n\nRunning test 1:  Output is" (test-one (deref player)))
(println "\n\nRunning test 2:  Output is" (test-two (deref player)))
(println "\n\nRunning test 3:  Output is" (test-three (deref player)))
(println "\n\nRunning test 4:  Output is" (test-four (deref player)))
