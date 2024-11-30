(ns backend.core
  (:require
   [ring.adapter.jetty :as jetty]
   [ring.middleware.file :as ring-file]
   [ring.middleware.file-info :as ring-file-info]))

(defn main-handler [_]
  {:status 200
   :headers {"content-type" "text/plain"}
   :body "Hello World!"})

(def handler
  (-> main-handler
      (ring-file/wrap-file "resources/public")
      (ring-file/wrap-file "compiled-resources/public")
      (ring-file-info/wrap-file-info)))

(defn -main [& _args]
  (jetty/run-jetty handler {:port 3000}))

(comment
  (+ 1 1))
