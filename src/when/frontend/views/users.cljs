(ns frontend.views.users
  (:require [helix.core :refer [defnc $]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            ["@mui/material" :as mui]))

(def table-config
  {:columns
   [{:field :id :headerName "ID" :width 100}
    {:field :name :headerName "Name" :width 200}
    {:field :email :headerName "Email" :width 200}]
   :pagination
   {:page 0
    :page-size 10
    :page-size-options [10 25 50 100]}})

(defn get-users []
  #_(js/fetch "/api/v1/users"
            {:method "GET"
             :headers {"Content-Type" "application/json"}})
  [{:id 1 :name "John Doe" :email "john.doe@example.com"}
   {:id 2 :name "Jane Doe" :email "jane.doe@example.com"}])

(defnc Header []
  (d/$d mui/TableHead
        (d/$d mui/TableRow
              (for [{:keys [field headerName]} (get table-config :columns)]
                (d/$d mui/TableCell
                      {:key field}
                      headerName))
              (d/$d mui/TableCell
                    {:key "actions"}
                    "Actions"))))

(defnc Body []
  (d/$d mui/TableBody
        (for [user (get-users)]
          (d/$d mui/TableRow
                {:key (:id user)}
                (for [{:keys [field]} (get table-config :columns)]
                  (d/$d mui/TableCell
                        {:key (str (get user field))}
                        (get user field)))
                (d/$d mui/TableCell
                      {:key "actions"}
                      (d/$d mui/Button
                            {:variant "contained"
                             :color "primary"}
                            "Edit"))))))

(defnc UsersList []
  (d/$d mui/Paper
        {:sx #js{:height 400
                 :width "100%"}}
        (d/$d mui/TableContainer
              {:component mui/Paper}
              (d/$d mui/Table
                    ($ Header)
                    ($ Body)))))
