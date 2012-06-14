(defproject hacker "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [org.clojure/java.jdbc "0.1.1"]
                 [org.xerial/sqlite-jdbc "3.7.2"]
                 ]
  :dev-dependencies [[midje "1.4.0"]
                     [com.stuartsierra/lazytest "1.2.3"]
                     ]
  :repositories {"stuart" "http://stuartsierra.com/maven2"}
  
  :main hacker.core



  )