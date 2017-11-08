(ns clj-web-editor.core.state.events
  (:require 
    [re-frame.core :refer [reg-fx reg-event-fx]]
    [clj-web-editor.core.state.eval :refer [eval-fx]]))

(reg-fx :eval eval-fx)

(reg-event-fx :init
  (fn [_ _]
    {:db {:code ""}}))

(reg-event-fx :run-code
  (fn [{db :db} _]
    {:eval [(db :code) (fn [])]}))

(reg-event-fx :update-code
  (fn [{db :db} [_ code]]
    {:db (assoc db :code code)}))
