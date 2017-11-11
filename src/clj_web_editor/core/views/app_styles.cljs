(ns clj-web-editor.core.views.app-styles)

(def width-column-1 "40%")
(def width-column-2 "30%")
(def width-column-3 "30%")

(defn >app []
  [:>.app
   {:display "flex"
    :flex-wrap "wrap"
    :width "100%"}

   [:>.editor
    {:width width-column-1
     :height "100vh"}

    [:>.CodeMirror
     {:padding "1em"
      :width "100%"
      :height "100%"
      :box-sizing "border-box"}

     [:.CodeMirror-selected
      {:background "#191d35 !important"}]

     [:.parinfer-error 
      {:background "#9e0d0d"}]

     [:.parinfer-paren-trail 
      {:opacity "0.4"}]]]

   [:>#workspace
    {:width width-column-2
     :height "100vh"
     :overflow "auto"}]

   [:>.console
    {:width width-column-3
     :height "100vh"
     :padding "1em"
     :box-sizing "border-box"
     :background "black"
     :font-family "monospace"
     :overflow-y "auto"}

    [:>.controls 
     {:display "flex"
      :justify-content "space-between"}

     [:>.run

      [:>.icon
       {:height "1em" 
        :width "1em"
        :vertical-align "middle"
        :margin-left "0.25em"}

       [:&.waiting
        {:fill "#ffffff"}]

       [:&.error
        {:fill "#e77434"}]

       [:&.success
        {:fill "#de95fe"}]]

      [:>button]]]

    [:>.messages

     [:>.message
      {:color "white"
       :margin-top "1em"
       :border-radius "5px"
       :padding "1em"
       }

      [:&.warning
       {:background "#ce7804"}]

      [:&.error
       {:background "#9e0d0d"}]

      [:&.print
       {:background "#4a4a4a"}]

      [:>.type
       {:text-transform "uppercase"
        :opacity 0.5
        :font-size "0.75em"
        :margin-bottom "0.25rem"}]

      [:>.content
       {:white-space "pre"}]]]]])
