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
                  ;; Please note that using the index handler as not-found
                  ;; handler results in the server returning 200 ok for all
                  ;; unknown routes. This is bad for SEO.
                  ;; It should be either handled by FE router or by a shared
                  ;; router in a common file.
                  (reitit-ring/create-default-handler 
                   {:not-found (partial static/index-handler system)})))]
    (handler request)))
