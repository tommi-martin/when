(ns frontend.subs.router
  (:require [refx.alpha :refer [reg-sub]]))

(reg-sub
 :router-location
 (fn [db _]
   (get-in db [:app :router-location])))

(reg-sub
 :csrf-token
 (fn [db _]
   (get-in db [:app :csrf-token])))
