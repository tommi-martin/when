(ns frontend.core
  (:require ["react" :as react]
            ["react-dom/client" :as rdom]
            ["@mui/icons-material" :as mui-icons]
            ["@mui/material" :as mui]
            [frontend.events.app]
            [frontend.subs.core]
            [frontend.views.events :as view.events]
            [frontend.views.frontpage :as view.index]
            [frontend.views.users :as view.users]
            [helix.core :refer [defnc $ <>]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            [refx.alpha :refer [use-sub dispatch-sync dispatch]]
            [reitit.frontend :as rf]
            [reitit.frontend.easy :as rfe]))

(defonce root (atom nil))
(defonce drawer-width 240)

(defnc NotFound []
  (d/div "Not found"))

(def main-menu
  [{:href ::frontpage
    :label "Home"
    :icon mui-icons/Home}
   {:href ::events
    :label "Events"
    :icon mui-icons/Bento}
   {:href ::users
    :label "Users"
    :icon mui-icons/People}])

(defnc MenuItem 
  [{:keys [href label icon]}]
  (d/$d mui/ListItem
        {:disable-padding true}
        (d/$d mui/ListItemButton
              {:component "a"
               :href (rfe/href href)}
              (d/$d mui/ListItemIcon (d/$d icon))
              (d/$d mui/ListItemText label))))

(defnc Menu []
  (d/$d mui/Drawer
        {:anchor "left"
         :variant "permanent"
         :sx #js{:width drawer-width
                 :flexShrink 0
                 "& .MuiDrawer-paper" #js{:width drawer-width
                                          :box-sizing "border-box"}}}
        (d/$d mui/List
              {:component "nav"}
              (for [props main-menu]
                ($ MenuItem {:key (:label props)
                             & props})))))

(defnc App []
  (let [location (use-sub [:router-location])]
    (d/$d mui/Box
          {:sx #js{:display "flex"}}
          (d/$d mui/CssBaseline)
          ($ Menu)
          (d/$d mui/Container
                ($ (get-in location [:data :view] NotFound))))))

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
   ["/users" {:name ::users
              :view view.users/UsersList}]
   ["/users/:id" {:name ::user-id}]
   ["/event/:id" {:name ::event-id}]])

(defn ^:dev/after-load init []
  (dispatch-sync [:init])
  (rfe/start!
   (rf/router routes)
   (fn [m] (dispatch [:router-location m]))
   {:use-fragment false})
  (render App))
