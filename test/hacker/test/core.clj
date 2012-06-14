(ns hacker.test.core
  (:use [hacker.core])
  (:use [hacker.player])
  (:use [hacker.equipment])
  (:use [hacker.skills])
  (:use [hacker.tasks])
  (:use [midje.sweet]))

;; matching with regex
(fact
  "O wad some Pow'r the giftie gie us. To see oursels as ithers see us!"
  => #"oursels")

(fact (+ 1 1) => even?)
(fact (+ 1 2) =not=> even?)

(defn function-that-returns-a-collection
  [something]
  [3, 2, 1])

;; claim that 3 is among one of the values returned by the function.
(fact
  (some #{3} (function-that-returns-a-collection 3)) => truthy)

;; is the same as...

(fact
  (function-that-returns-a-collection 3) => (contains 3))


;; when you need an exact match: (just <match>)
(fact
  (function-that-returns-a-collection 24) => (just [3 2 1]))

;; using "provided" to name a result from a 'wishful-thinking' function
;; (if you need that wishful-thinking function in the one you're testing)
(unfinished animals)
(defn animal-set [animals]
  (if (= animals ["cow" "horse"])
    (set animals)
    false))

(fact 
      (animal-set (animals)) => #{"cow" "horse"}
      (provided 	      
          (animals) => ["cow" "horse"]))



;; Test initialize-game function
;; run set-player and check if the player map was created by looking up the background story
(set-player)
(fact
  (player :background-story) => "You come from a long line of male prostitutes, once an honored family but now fallen from grace. The first in six generations to learn how to read, you've decided to give up the life of the night, and turn your attention to computers. Who knows what fame and fortune -- and discoveries -- might await?  Well, I do.  Because I'm writing this game.")


(set-equipment)
(fact
  (get-in @equipment [:console-and-keyboard :defense-value]) => 30)


(set-skills)
  (fact
  (get-in skills [:anonymity :6 :level-name]) => "International Person Of Mystery")

  
(set-tasks)
(fact
  (deref tasks) => {})

