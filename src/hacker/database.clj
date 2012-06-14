(ns hacker.db
  (require
    `[clojure.java.jdbc :as jdbc]))

;; database parameters
(def db-spec {:classname "org.sqlite.JDBC"
              :subprotocol "sqlite"
              :subname "hacker.db"})

(jdbc/with-connection db-spec)

         