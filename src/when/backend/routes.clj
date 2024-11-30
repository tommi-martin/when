(ns backend.routes
  (:require [backend.system :as-alias system]
            [clojure.tools.logging :as log]
            [next.jdbc :as jdbc]
            [reitit.ring :as reitit-ring]
            [ring.util.response :as resp]))

(defn debug-postgres
  [db]
  (let [{:keys [planet]} (jdbc/execute-one!
                          db
                          ["SELECT 'earth' as planet"])]
    (log/info (str "planet: " planet))))

(defn index-handler
  [{::system/keys [db]} request]
  (debug-postgres db)
  (or (resp/resource-response (:uri request) {:root "public"})
      (-> (resp/resource-response "index.html" {:root "public"})
          (resp/content-type "text/html"))))

(defn not-found-handler
  [_request]
  {:status 404
   :headers {"content-type" "text/html"}
   :body "Not Found!"})

(defn routes
  [system]
  [["/" {:get {:handler (partial #'index-handler system)}}]
   ["/*" {:get {:handler (reitit-ring/create-resource-handler
                          {:not-found-handler #'not-found-handler})}}]])

(defn root-handler
  [system request]
  (log/info (str (:request-method request) " - " (:uri request)))
  (let [handler (reitit-ring/ring-handler
                 (reitit-ring/router
                  (routes system))
                 #'not-found-handler)]
    (handler request)))
