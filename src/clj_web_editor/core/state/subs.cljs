(ns clj-web-editor.core.state.subs
  (:require
    [re-frame.core :refer [reg-sub]]))

(reg-sub :code
  (fn [db _]
    (db :code)))

(reg-sub :console
  (fn [db _]
    (db :console)))

(reg-sub :eval-state
  (fn [db _]
    (db :eval-state)))
