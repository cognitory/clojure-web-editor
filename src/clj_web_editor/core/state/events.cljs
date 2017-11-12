(ns clj-web-editor.core.state.events
  (:require 
    [clojure.string :as string]
    [re-frame.core :refer [reg-fx reg-event-fx dispatch]]
    [clj-web-editor.core.state.eval :refer [eval-fx]]))

(reg-fx :eval eval-fx)

(reg-event-fx :init
  (fn [_ _]
    {:db {:code (.. js/localStorage (getItem "code"))
          :eval-state :none ; :waiting :success :error
          :console []}}))

(reg-event-fx :console-log
  (fn [{db :db} [_ type message]]
    {:db (update db :console conj {:type type
                                   :content message})}))

(reg-event-fx :console-clear
  (fn [{db :db} _]
    {:db (assoc db :console [])}))

(reg-event-fx :save-code
  (fn [{db :db} _]
    (.. js/localStorage (setItem "code" (db :code)))
    {}))

(reg-event-fx :set-eval-state
  (fn [{db :db} [_ state]]
    {:db (assoc db :eval-state state)}))

(reg-event-fx :run-code
  (fn [{db :db} _]
    {:dispatch-n [[:console-clear]
                  [:set-eval-state :waiting]]
     :eval {:code (db :code)
            :on-print (fn [& args]
                        (dispatch [:console-log :print args]))
            :on-warning (fn [warning]
                          (dispatch [:console-log :warning [warning]]))
            :on-success (fn [value]
                          (dispatch [:set-eval-state :success]))
            :on-error (fn [error]
                        (dispatch [:set-eval-state :error])
                        (dispatch [:console-log :error [(str (aget error "cause"))]]))}}))

(reg-event-fx :update-code
  (fn [{db :db} [_ code]]
    {:db (assoc db :code code)
     :dispatch-n [[:save-code]
                  [:set-eval-state :none]]}))
 
