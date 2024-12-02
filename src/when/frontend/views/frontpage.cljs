(ns frontend.views.frontpage
  (:require [helix.core :refer [defnc $]]
            [helix.dom :as d]
            [helix.hooks :as hooks]))

(defnc Greeting
  [{:keys [name]}]
  (d/div "Hello, " name))

(defnc Dashboard []
  (let [[state set-state] (hooks/use-state {:name "helix user"})]
    (d/div "Dashboard"
           ($ Greeting {:name (:name state)})
           (d/input {:value (:name state)
                     :on-change #(set-state assoc
                                            :name
                                            (.. % -target -value))}))))
