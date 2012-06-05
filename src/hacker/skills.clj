(ns hacker.skills
  (:import java.io.File))

(declare skills)

(defn load-skill [skills file]
  (let [thing (read-string (slurp (.getAbsolutePath file)))]
    (conj skills
          {(keyword (.getName file))
           {:name (keyword (.getName file))
            :0 (:0 thing)
            :1 (:1 thing)
            :2 (:2 thing)
            :3 (:3 thing)
            :4 (:4 thing)
            :5 (:5 thing)
            :6 (:6 thing)
            :7 (:7 thing)
            :8 (:8 thing)
            :9 (:9 thing)
;;            :level-name (:level-name thing)
;;            :description (:description thing)
;;            :skill-points-required (:skill-points-required thing)
}})))


(defn load-all-skills [dir]
  "Given a path, return a map with an entry corresponding to each file
in it. Files should be maps containing skill data for one skill."
  (reduce load-skill {} (.listFiles (java.io.File. dir))))


(defn set-skills
  "Set hacker.skills/skills to a map of skills corresponding to each
 file in dir. This function should be used only once at startup,
 so having a def inside the function body should be OK. Defaults to
 looking in data/skills/."
  ([dir]
     (def skills (load-all-skills dir)))
  
  ([] (set-skills "data/skills/")))
