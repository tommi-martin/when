(ns backend.core
  (:require
   [backend.system :as system]))

(defn -main [& _args]
  (system/start-system))
