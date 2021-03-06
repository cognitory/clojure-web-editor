(ns clj-web-editor.core.views.app
  (:require
    [re-frame.core :refer [dispatch subscribe]]
    [clj-web-editor.core.views.editor :refer [editor-view]]
    [clj-web-editor.core.views.console :refer [console-view]]
    [clj-web-editor.core.views.styles :refer [styles-view]]))

(defn app-view []
  [:div.app 
   {:on-key-down (fn [e]
                   (when (and e.ctrlKey (= 13 e.keyCode))
                     (dispatch [:run-code])))}
   [styles-view]
   [editor-view @(subscribe [:code])]
   [:div {:id "workspace"}]
   [console-view]])
