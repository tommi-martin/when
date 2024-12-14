(ns frontend.views.events
  (:require [helix.core :refer [defnc]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            [refx.alpha :refer [dispatch]]))

(def form-default-state {:title "" :description ""})

(defn state-input
  [set-state field event]
  (set-state 
   (fn [values]
     (assoc values field (-> event .-target .-value)))))

(defn submit-event
  [form-state event]
  (.preventDefault event)
  (dispatch [:create-event form-state]))

(defnc Events []
  (d/div
   (d/h1 "Create new event")
   (let [[form-state set-state] (hooks/use-state form-default-state)
         change-fn (partial state-input set-state)]
     (d/form {:method "post"
              :action "/api/v1/events"
              :on-submit #(submit-event form-state %)}
             (d/label "Title")
           ;; needs anti-forgery token
             (d/input {:type "text"
                       :name "title"
                       :on-change #(change-fn :title %)
                       :placeholder "Title"})
             (d/label "Description")
             (d/input {:type "text"
                       :name "description"
                       :on-change #(change-fn :description %)
                       :placeholder "Description"})
             (d/input {:type "submit"
                       :value "Create"})))))
