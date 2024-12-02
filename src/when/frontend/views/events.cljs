(ns frontend.views.events
  (:require [helix.core :refer [defnc <>]]
            [helix.dom :as d]
            [refx.alpha :refer [use-sub]]))

(defnc Events []
  (d/div
   (d/h1 "Create new event")
   (let [token (use-sub [:csrf-token])]
     (<>
      (prn token)
      (d/p "test"))) 
   (d/form {:method "post"
            :action "/api/v1/events"}
           (d/label "Title")
           ;; needs anti-forgery token
           (d/input {:type "text"
                     :name "title"
                     :placeholder "Title"})
           (d/label "Description")
           (d/input {:type "text"
                     :name "description"
                     :placeholder "Description"})
           (d/input {:type "submit"
                     :value "Create"}))))
