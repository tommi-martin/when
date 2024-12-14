(ns frontend.events.app
  (:require [frontend.connection.api :as api]
            [refx.alpha :refer [reg-event-db reg-event-fx dispatch]]
            [refx.db :refer [app-db]]))

(reg-event-fx
 :init
 (fn []
   (let [db {:app {:csrf-token nil
                   :router-location nil}}]
     (api/fetch (fn [body]
                  (dispatch [:csrf-token (:csrf-token body)]))
                "/api/v1/init")
     {:db db :fx []})))

(reg-event-fx
 :create-event
 (fn [_ [_ form]]
   (api/post "/api/v1/events" form)))

(reg-event-db
 :router-location
 (fn [db [_ router-match]]
   (assoc-in db [:app :router-location] router-match)))

(reg-event-db
 :csrf-token
 (fn [db [_ token]]
   (assoc-in db [:app :csrf-token] token)))

(comment
  (api/fetch (fn [body]
               (dispatch [:csrf-token (:csrf-token body)]))
             "/api/v1/init")

  @app-db)
