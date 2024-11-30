(ns backend.system
  (:require [backend.routes :as routes]
            [next.jdbc.connection :as connection]
            [ring.adapter.jetty :as jetty])
  (:import (com.zaxxer.hikari HikariDataSource)
           (org.eclipse.jetty.server Server)))

(set! *warn-on-reflection* true)

(defn start-db
  []
  (connection/->pool HikariDataSource
                     {:dbtype "postgres"
                      :dbname "mydb"
                      :username (System/getProperty "user.name")}))

(defn stop-db
  [db]
  (HikariDataSource/.close db))

(defn start-server
  [system]
  (jetty/run-jetty
   (partial #'routes/root-handler system)
   {:port 3000 :join? false}))

(defn stop-server
  [server]
  (Server/.stop server))

(defn start-system
  []
  (let [system {::db (start-db)}]
    (merge system {::server (start-server system)})))

(defn stop-system
  [system]
  (stop-server (::server system))
  (stop-db (::db system)))
