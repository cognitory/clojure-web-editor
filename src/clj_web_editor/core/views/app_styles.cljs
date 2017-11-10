(ns clj-web-editor.core.views.app-styles)

(defn >app []
  [:>.app
   {:display "flex"
    :width "100%"}

   [:>.run
    {:position "absolute"
     :top 0
     :right "50%"
     :margin "1em"
     :z-index 1000}

    [:>.icon
     {:height "1em" 
      :width "1em"
      :vertical-align "middle"
      :margin-right "0.25em"}

     [:&.waiting
      {:fill "#ffffff"}]

     [:&.error
      {:fill "#e77434"}]

     [:&.success
      {:fill "#de95fe"}]]

    [:>button]]
   
   [:>.editor
    {:width "50%"}

    [:>.CodeMirror
     {:padding "1em"
      :height "100vh"
      :width "100%"
      :box-sizing "border-box"}
     
     [:.CodeMirror-selected
      {:background "#191d35 !important"}]

     [:.parinfer-error 
      {:background "#9e0d0d"}]

     [:.parinfer-paren-trail 
      {:opacity "0.4"}]]]

   [:>#workspace
    {:width "50%"}]
   
   [:>.console
    {:position "absolute"
     :bottom 0
     :left 0
     :margin "1em"
     :font-family "monospace"}

    [:>.messages

     [:>.message
      {:color "white"
       :margin-top "1em"
       :border-radius "5px"
       :padding "1em"}

      [:&.warning
       {:background "#ce7804"}]

      [:&.error
       {:background "#9e0d0d"}]

      [:&.print
       {:background "#4a4a4a"}]
      
      [:>.type
       {:text-transform "uppercase"
        :opacity 0.5
        :font-size "0.75em"}]]]]])
