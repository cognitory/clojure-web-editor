(ns clj-web-editor.core.views.app
  (:require
    [re-frame.core :refer [dispatch subscribe]]
    [clj-web-editor.core.views.editor :refer [editor-view]]
    [clj-web-editor.core.views.console :refer [console-view]]
    [clj-web-editor.core.views.styles :refer [styles-view]]))

(defn icon [id class]
  [:svg.icon {:class class}
   [:use {:xlink-href (str "/icons.svg#icon-" id)}]])

(defn run-button-view []
  [:div.run
   (case @(subscribe [:eval-state])
     :none nil
     :waiting [icon "ellipsis-h" "waiting"]
     :success [icon "check-circle" "success"]
     :error [icon "exclamation-triangle" "error"])
   [:button.run
    {:on-click (fn []
                 (dispatch [:run-code]))
     :title "Ctrl-Enter"}
    "Run"]])

(defn app-view []
  [:div.app 
   {:on-key-down (fn [e]
                   (when (and e.ctrlKey (= 13 e.keyCode))
                     (dispatch [:run-code])))}
   [styles-view]
   [run-button-view]
   [editor-view @(subscribe [:code])]
   [:div {:id "workspace"}]
   [console-view]])
