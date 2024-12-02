(ns frontend.core
  (:require ["react" :as react]
            ["react-dom/client" :as rdom]
            [frontend.events.app]
            [frontend.subs.core]
            [frontend.views.events :as view.events]
            [frontend.views.frontpage :as view.index]
            [helix.core :refer [defnc $ <>]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            [refx.alpha :refer [use-sub dispatch-sync dispatch]]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]))

(defonce root (atom nil))

(defnc NotFound []
  (d/div "Not found"))

(defnc App []
  (let [location (use-sub [:router-location])]
    (<>
     (d/div
      (d/ul
       (d/li (d/a {:href (rfe/href ::frontpage)}
                  "home"))
       (d/li (d/a {:href (rfe/href ::events)}
                  "Events"))))
     ($ (get-in location [:data :view] NotFound)))))

(defn react-root
  []
  (when-let [elem (js/document.getElementById "app")]
    (when-not @root
      (reset! root (rdom/createRoot elem)))
    @root))

(defn render [app]
  (.render (react-root)
           ($ react/StrictMode
              ($ app))))

;; TODO: use common file for routes to make sure backend server sends correct http status.
(def routes
  [["/" {:name ::frontpage
         :view view.index/Dashboard}]
   ["/events" {:name ::events
               :view view.events/Events}]
   ["/event" {:name ::event}]
   ["/event/:id" {:name ::event-id}]])

(defn ^:dev/after-load init []
  (dispatch-sync [:init])
  (rfe/start!
   (rf/router routes)
   (fn [m] (dispatch [:router-location m]))
   {:use-fragment false})
  (render App))
