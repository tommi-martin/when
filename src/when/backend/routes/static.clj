(ns backend.routes.static
  (:require [backend.middleware :as middleware]
            [backend.system :as-alias system]
            [clojure.tools.logging :as log]
            [ring.util.anti-forgery :as af]
            [next.jdbc :as jdbc]
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
  (prn "------")
  (prn (af/anti-forgery-field))
  (prn "------")
  (or (resp/resource-response (:uri request) {:root "public"})
      (-> (resp/resource-response "index.html" {:root "public"})
          (resp/content-type "text/html"))))

(defn routes
  [system]
  ;; TODO: use common file for routes to make sure backend server sends correct
  ;; http status. both backend and frontend should have the same route source
  ;; otherwise maintenance is a pain.
  ["" {:middleware (middleware/standard-html-route-middleware system)}
   ["/" {:get {:handler (partial #'index-handler system)}}]
   ["/events" {:get {:handler (partial #'index-handler system)}}]
   ["/event" {:get {:handler (partial #'index-handler system)}}]
   ["/event/:id" {:get {:handler (partial #'index-handler system)}}]])
