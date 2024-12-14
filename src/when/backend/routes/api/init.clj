(ns backend.routes.api.init
  (:require [ring.middleware.anti-forgery :refer [*anti-forgery-token*]]
            [ring.util.anti-forgery :as af]))

;; TODO: CSRF may requires additional work to function on api
;; This system might need to be taken down and rebuilt to the user validation.
;; Recheck after login has been implemented.
(defn get-handler [_system _request]
  {:status 200
   :body {:csrf-token (str (force *anti-forgery-token*))} 
   :headers {"content-type" "application/json"}})

(comment 
  (ring.util.anti-forgery/anti-forgery-field)
  (force *anti-forgery-token*))
