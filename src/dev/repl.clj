(ns repl
  (:require
   [backend.core :as srv]
   [ring.adapter.jetty :as jetty]
   [shadow.cljs.devtools.api :as shadow]))

(defonce jetty-ref (atom nil))

(defn start
  {:shadow/requires-server true}
  []
  (shadow/watch :frontend)

  (reset! jetty-ref
          (jetty/run-jetty #'srv/handler
                           {:port 3000
                            :join? false}))
  (println "--------------------------------")
  (println "Started server on port 3000")
  (println "http://localhost:3000")
  (println "--------------------------------")
  ::started)

(defn stop []
  (when-some [jetty @jetty-ref]
    (reset! jetty-ref nil)
    (.stop jetty))
  ::stopped)

(defn go []
  (stop)
  (start))
