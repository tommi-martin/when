(ns backend.routes.api
  (:require [backend.routes.api.user :as api.user]))

(defn debug-handler [& _args]
  {:status 200
   :body "Hello, World!"
   :headers {"content-type" "text/plain"}})

(defn routes
  [system]
  [["/api/v1"
    ["/ping" {:get {:handler (constantly {:status 200 :body "pong" :headers {"content-type" "text/plain"}})}}]
    ["/user" {:get {:handler (partial #'api.user/get-handler system)}
              :post {:handler (partial #'api.user/post-handler system)}}]]])
