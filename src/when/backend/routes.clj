(ns backend.routes
  (:require [backend.routes.api :as api]
            [backend.routes.static :as static]
            [backend.system :as-alias system]
            [clojure.tools.logging :as log]
            [reitit.ring :as reitit-ring]))

(defn routes
  [system]
  [""
   (api/routes system)
   (static/routes system)])

(defn root-handler
  [system request] 
  (log/info (str (:request-method request) " - " (:uri request)))
  (let [handler (reitit-ring/ring-handler
                 (reitit-ring/router
                  (routes system))
                 (reitit-ring/routes
                  (reitit-ring/create-resource-handler
                   {:path "/"})
                  (reitit-ring/create-default-handler
                   {:not-found
                    (constantly
                     {:status 404
                      :body "404 not found! You're lost! Go back!"})})))]
    (handler request)))
