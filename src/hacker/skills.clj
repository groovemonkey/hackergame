(ns hacker.skills)
(declare skills)

(defn load-skill [file]
  (let [thing (read-string (slurp file))]
    (conj skills
          {(keyword (.getName file))
           {:name (keyword (.getName file))
            :level-name (:level-name thing)
            :description (:description thing)
            :skill-points-requierd (:skill-points-required thing)
}})))


(defn load-all-skills [dir]
  "Given a path, return a map with an entry corresponding to each file
in it. Files should be maps containing skill data for one skill."
  (reduce load-skill (.listFiles (java.io.File. dir))))


(defn set-skills
  "Set hacker.skills/skills to a map of skills corresponding to each
 file in dir. This function should be used only once at mire startup,
 so having a def inside the function body should be OK. Defaults to
 looking in data/skills/."
  ([dir]
     (def skills (load-all-skills dir)))
  ([] (set-skills "/home/dave/code/hacker/data/skills/")))
