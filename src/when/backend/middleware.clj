(ns backend.middleware
  (:require [muuntaja.middleware :as muuntaja-mw]
            [ring.middleware.defaults :as mw-defaults]))

(defn standard-api-router-middleware
  [_]
  [(fn [handler]
     (-> (mw-defaults/wrap-defaults handler 
                                    mw-defaults/api-defaults)
         muuntaja-mw/wrap-format))])

(defn standard-html-route-middleware
  [_system]
  [#(mw-defaults/wrap-defaults % mw-defaults/site-defaults)])
