(ns backend.routes.api.events)

(defn post-handler
  [_system _request]
  {:status 200
   :body "pong"
   :headers {"content-type" "text/plain"}})
