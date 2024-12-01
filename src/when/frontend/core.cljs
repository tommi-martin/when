(ns frontend.core
  (:require ["react" :as react]
            ["react-dom/client" :as rdom]
            [frontend.events.app]
            [frontend.subs.core]
            [helix.core :refer [defnc $ <>]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            [refx.alpha :refer [use-sub dispatch-sync dispatch]]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]))

(defonce root (atom nil))

(defnc Greeting
  [{:keys [name]}]
  (d/div "Hello, " name))

(defnc Dashboard []
  (let [[state set-state] (hooks/use-state {:name "helix user"})]
    (d/div "Dashboard"
           ($ Greeting {:name (:name state)})
           (d/input {:value (:name state)
                     :on-change #(set-state assoc :name (.. % -target -value))}))))

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

(defnc Debug []
  (d/div "Debug"))

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

(def routes
  [["/" {:name ::frontpage
         :view Dashboard}]
   ["/events" {:name ::events
               :view Debug}]
   ["/events/:id" {:name ::event}]])

(defn ^:dev/after-load init []
  (dispatch-sync [:init])
  (rfe/start!
   (rf/router routes)
   (fn [m] (dispatch [:router-location m]))
   {:use-fragment false})
  (render App))
