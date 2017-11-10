(ns clj-web-editor.core.views.console
  (:require
    [re-frame.core :refer [dispatch subscribe]]
    [zprint.core :refer [zprint-str]]))

(defn format-code [code]
  (zprint-str code 50 {:style :community
                       :parse-string? true
                       :map {:comma? false
                             :force-nl? true}}))

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
            [:div.content
             (case (message :type)
               :error (message :content)
               :warning (message :content)
               :print (format-code (message :content)))]]))])))
