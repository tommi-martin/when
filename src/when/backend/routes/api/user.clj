(ns backend.routes.api.user)

(defn get-handler
  [& _args]
  {:status 200
   :body "{\"user\": \"tester\"}"
   :headers {"content-type" "application/json"}})

(defn post-handler
  [& _args]
  {:status 200
   :body "{\"accepted\": true}"
   :headers {"content-type" "application/json"}})
