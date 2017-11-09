(ns clj-web-editor.core.views.app-styles)

(defn >app []
  [:>.app
   {:display "flex"
    :width "100%"}

   [:>button.run
    {:position "absolute"
     :top 0
     :right "50%"
     :margin "1em"
     :z-index 1000}]
   
   [:>#editor
    {:width "50%"}

    [:>.CodeMirror
     {:padding "1em"
      :height "100vh"
      :width "100%"
      :box-sizing "border-box"}
     
     [:.parinfer-error 
      {:background "#FFBEBE"}]

     [:.parinfer-paren-trail 
      {:opacity "0.4"}]]]

   [:>#workspace
    {:width "50%"}]])
