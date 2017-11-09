(ns clj-web-editor.core.state.events
  (:require 
    [re-frame.core :refer [reg-fx reg-event-fx dispatch]]
    [clj-web-editor.core.state.eval :refer [eval-fx]]))

(reg-fx :eval eval-fx)

(reg-event-fx :init
  (fn [_ _]
    {:db {:code ""
          :console []}}))

(reg-event-fx :console-log
  (fn [{db :db} [_ type message]]
    {:db (update db :console conj {:type type
                                   :content message})}))

(reg-event-fx :console-clear
  (fn [{db :db} _]
    {:db (assoc db :console [])}))

(reg-event-fx :run-code
  (fn [{db :db} _]
    {:dispatch [:console-clear]
     :eval {:code (db :code)
            :on-print (fn [& args]
                        (dispatch [:console-log :print args]))
            :on-warning (fn [warning]
                          (dispatch [:console-log :warning warning]))
            :on-success (fn [value])
            :on-error (fn [error]
                        (dispatch [:console-log :error (str (aget error "cause"))]))}}))

(reg-event-fx :update-code
  (fn [{db :db} [_ code]]
    {:db (assoc db :code code)}))
 
