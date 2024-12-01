(ns frontend.events.app
  (:require [refx.alpha :refer [reg-event-db reg-event-fx]]))

(reg-event-fx
 :init
 (fn []
   (let [db {:app {:router-location nil}}] ;; TODO: init location from browser url
     {:db db :fx []})))

(reg-event-db
 :router-location
 (fn [db [_ router-match]]
   (assoc-in db [:app :router-match] router-match)))
