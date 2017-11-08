(ns clj-web-editor.core.views.styles
  (:require 
    [garden.core :as garden]
    [clj-web-editor.core.views.app-styles :refer [>app]]))

(defn styles-view []
  [:style
   {:type "text/css"
    :dangerouslySetInnerHTML
    {:__html 
     (garden/css 
       [:body
        {:margin 0
         :padding 0}]

       [:#app
        (>app)])}}])
