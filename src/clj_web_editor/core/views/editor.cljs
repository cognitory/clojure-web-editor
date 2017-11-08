(ns clj-web-editor.core.views.editor
  (:require 
    [cljsjs.codemirror]
    [cljsjs.codemirror.mode.clojure]
    [cljsjs.codemirror.keymap.vim]
    [cljsjs.parinfer]
    [cljsjs.parinfer-codemirror]
    [reagent.core :as r]
    [re-frame.core :refer [dispatch subscribe]]))

(defn editor-view []
  (let [code (subscribe [:code])]
    (r/create-class
      {:component-did-mount
       (fn []
         (let [cm (doto 
                    (js/CodeMirror 
                      (.. js/document (getElementById "editor"))
                      (clj->js {:theme "railscasts"
                                :mode "clojure"
                                :autofocus true
                                :matchBrackets true
                                :value @code}))
                    (.on "change" 
                         (fn [editor]
                           (dispatch [:update-code (.getValue editor)]))))]
           (js/parinferCodeMirror.init cm "smart" #js {:forceBalance true})))

       :reagent-render
       (fn []
         [:div {:id "editor"}])})))
