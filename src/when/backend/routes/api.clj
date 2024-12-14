(ns backend.routes.api
  (:require [backend.middleware :as middleware]
            [backend.routes.api.events :as api.events]
            [backend.routes.api.init :as api.init]
            [backend.routes.api.user :as api.user]))

(defn routes
  [system]
  [["/api/v1" {:middleware (middleware/standard-api-router-middleware system)}
    ["/ping" {:get {:handler (constantly {:status 200 :body "pong" :headers {"content-type" "text/plain"}})}}]
    ["/init" {:get {:handler (partial #'api.init/get-handler system)}}]
    ["/events" {:post {:parameters {:body {:title string?
                                           :description string?}}
                       :handler (partial #'api.events/post-handler system)}}]
    ["/user" {:get {:handler (partial #'api.user/get-handler system)}
              :post {:handler (partial #'api.user/post-handler system)}}]]])
