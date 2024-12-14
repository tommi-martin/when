(ns backend.routes
  (:require [backend.routes.api :as api]
            [backend.routes.static :as static]
            [backend.system :as-alias system]
            [clojure.tools.logging :as log]
            [muuntaja.core :as m]
            [reitit.ring :as reitit-ring]
            [reitit.coercion.spec]
            [reitit.ring.coercion :as rrc]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [reitit.ring.middleware.parameters :as parameters]))

(defn routes
  [system]
  [""
   (api/routes system)
   (static/routes system)])

(defn root-handler
  [system request] 
  #_(log/info (str (:request-method request) " - " (:uri request)))
  (let [handler (reitit-ring/ring-handler
                 (reitit-ring/router
                  (routes system)
                  {:data {:coercion   reitit.coercion.spec/coercion
                          :muuntaja   m/instance
                          :middleware [parameters/parameters-middleware
                                       rrc/coerce-request-middleware
                                       muuntaja/format-response-middleware
                                       rrc/coerce-response-middleware]}})
                 (reitit-ring/routes
                  (reitit-ring/create-resource-handler
                   {:path "/"})
                  (reitit-ring/create-default-handler
                   {:not-found
                    (constantly
                     {:status 404
                      :body "404 not found! You're lost! Go back!"})})))]
    (handler request)))
