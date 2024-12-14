(ns backend.routes.api.events
  (:require [next.jdbc :as jdbc]
            [backend.system :as-alias system]))

(defn post-handler
  [{::system/keys [db]}
   {{:keys [title description]} :body-params}]
  (prn "hit post-handler: " title description)
  (jdbc/execute!
   db
   ["INSERT INTO users (username) VALUES (?)"
    title])
  #_(jdbc/execute!
   db
   ["INSERT INTO events (title, description) VALUES (?, ?)" 
    title
    description])
  {:status 200
   :body "pong"
   :headers {"content-type" "text/plain"}})
