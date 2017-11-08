(ns clj-web-editor.core.core
  (:require
    [reagent.core :as r]
    [re-frame.core :refer [dispatch-sync]]
    [clj-web-editor.core.state.subs]
    [clj-web-editor.core.state.events]
    [clj-web-editor.core.views.app :refer [app-view]]))

(enable-console-print!)

(defn render []
  (r/render-component [app-view]
    (.. js/document (getElementById "app"))))

(defn ^:export init []
  (dispatch-sync [:init])
  (render))

(defn ^:export reload []
  (render))

