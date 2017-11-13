(ns clj-web-editor.core.views.console
  (:require
    [re-frame.core :refer [dispatch subscribe]]))

(defn icon [id class]
  [:svg.icon {:class class}
   [:use {:xlink-href (str "./icons.svg#icon-" id)}]])

(defn run-button-view []
  [:div.run
   [:button.run
    {:on-click (fn []
                 (dispatch [:run-code]))
     :title "Ctrl-Enter"}
    "Run"]

   (case @(subscribe [:eval-state])
     :none nil
     :waiting [icon "ellipsis-h" "waiting"]
     :success [icon "check-circle" "success"]
     :error [icon "exclamation-triangle" "error"])])

(defn console-view []
  (let [messages @(subscribe [:console])]
    [:div.console {:class (when (empty? messages)
                            "empty")}
     [:div.controls
      [run-button-view]
      [:button {:on-click (fn [_]
                            (dispatch [:console-clear]))}
       "Clear"]]
     
     (into  
       [:div.messages]
       (for [message messages]
         [:div.message {:class (message :type)}
          [:div.type (message :type)]
          (into [:div.content]
                (for [item (message :content)]
                  (str item " ")))]))]))

(set! js/console.log 
      (fn [& args]
        (dispatch [:console-log :print args])))

(set! js/console.error 
      (fn [& args]
        (dispatch [:console-log :print args])))

(set! js/console.warn 
      (fn [& args]
        (dispatch [:console-log :warning args])))
