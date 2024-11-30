(ns repl
  (:require
   [backend.system :as system]
   [shadow.cljs.devtools.api :as shadow]))

(defonce jetty-ref (atom nil))

(defn start
  {:shadow/requires-server true}
  []
  (shadow/watch :frontend)

  (reset! jetty-ref (system/start-system))
  (println "--------------------------------")
  (println "Started server on port 3000")
  (println "http://localhost:3000")
  (println "--------------------------------")
  ::started)

(defn stop []
  (when-some [jetty @jetty-ref]
    (reset! jetty-ref nil)
    (system/stop-system jetty))
  ::stopped)

(defn go []
  (stop)
  (start))
