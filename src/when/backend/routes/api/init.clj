(ns backend.routes.api.init
  (:require [ring.middleware.anti-forgery :refer [*anti-forgery-token*]]
            [ring.util.anti-forgery :as af]))

(defn get-handler [_system _request]
  {:status 200
   :body {:csrf-token (str (force *anti-forgery-token*))}
   :headers {"content-type" "application/json"}})

(comment 
  (ring.util.anti-forgery/anti-forgery-field)
  (force *anti-forgery-token*))
