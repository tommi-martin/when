(ns repl
  (:require
   [backend.system :as system]
   [shadow.cljs.devtools.api :as shadow]))

(defonce system-state (atom nil))

(defn start
  {:shadow/requires-server true}
  []
  (shadow/watch :frontend)

  (reset! system-state (system/start-system))
  (println "--------------------------------")
  (println "Started server on port 3000")
  (println "http://localhost:3000")
  (println "--------------------------------")
  ::started)

(defn stop []
  (when-some [state @system-state]
    (reset! system-state nil)
    (system/stop-system state))
  ::stopped)

(comment
  @system-state)
