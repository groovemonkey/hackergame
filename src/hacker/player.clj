(ns hacker.player)
(declare player)

(defn load-player
  "removed the (.getAbsolutePath method because it wasn't working) -- so, needs a full path to the file as an argument"
  [file]
  (let [playermap (read-string (slurp file))]
    (conj {}
           {:name (:name playermap)
            :hacker-alias (:hacker-alias playermap)
            :background-story (:background-story playermap)
            :hacker-level (:hacker-level playermap)
            :total-points (:total-points playermap)
            :focus-recharge-rate (:focus-recharge-rate playermap)
            :equipment-owned (:equipment-owned playermap)
            :skills (:skills playermap)
            :money (:money playermap)
            :focus (:focus playermap)
            :tasks (:tasks playermap)
            })))

(defn set-player
  "Set hacker.player/player to the map in the player file.  This is run only once at startup. Defaults to looking in data/player."
  ([dir]
     (def player (load-player dir)))
  ([] (set-player "/home/dave/code/hacker/data/player")))
