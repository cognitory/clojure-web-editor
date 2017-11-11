(ns clj-web-editor.core.views.console
  (:require
    [re-frame.core :refer [dispatch subscribe]]))

(defn console-view []
  (let [messages @(subscribe [:console])]
    (when (seq messages)
      [:div.console
       [:button {:on-click (fn [_]
                             (dispatch [:console-clear]))}
        "Clear"]
       (into  
         [:div.messages]
         (for [message messages]
           [:div.message {:class (message :type)}
            [:div.type (message :type)]
            (into [:div.content]
                  (for [item (message :content)]
                    item))]))])))
