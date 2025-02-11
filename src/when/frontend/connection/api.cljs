(ns frontend.connection.api
  (:require [cljs-http.client :as http]
            [clojure.core.async :refer [go <!]]))

(defn fetch
  "Asynchronously call http api executes handler with response"
  [handler url]
  (go 
    (let [{:keys [status body]} (<! (http/get url))] 
      (if (= 200 status)
        (handler body)
        (js/console.error "Error fetching" url "status" status "Body" body)))))

(defn post
  [url body]
  (prn "hit post: " url body)
  (http/post 
   url 
   {:content-type "application/edn"
    :accept "application/edn"
    :body body}))
