(ns clj-web-editor.core.state.subs
  (:require
    [re-frame.core :refer [reg-sub]]))

(reg-sub :code
  (fn [db _]
    (db :code)))
