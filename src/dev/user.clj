(ns user
  (:require [backend.system :as system]
            [next.jdbc :as jdbc]
            [repl :as r])
  (:import (com.zaxxer.hikari HikariDataSource)
           (io.github.cdimascio.dotenv Dotenv)
           (org.eclipse.jetty.server Server)))

(defn restart []
  (r/stop)
  (r/start))

(defn server
  []
  (::system/server @r/system-state))

(defn db
  []
  (::system/db @r/system-state))

(defn env
  []
  (::system/env @r/system-state))

(comment
  (restart)

  (Dotenv/.get (env) "POSTGRES_USERNAME"))
