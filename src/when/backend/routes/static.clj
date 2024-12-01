(ns backend.routes.static
  (:require [backend.system :as-alias system]
            [clojure.tools.logging :as log]
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
  (or (resp/resource-response (:uri request) {:root "public"})
      (-> (resp/resource-response "index.html" {:root "public"})
          (resp/content-type "text/html"))))

(defn routes
  [system]
  [["/" {:get {:handler (partial #'index-handler system)}}]])
