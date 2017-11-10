(ns clj-web-editor.core.views.editor
  (:require 
    [cljsjs.codemirror]
    [cljsjs.codemirror.mode.clojure]
    [cljsjs.codemirror.keymap.vim]
    [cljsjs.parinfer]
    [cljsjs.parinfer-codemirror]
    [reagent.core :as r]
    [re-frame.core :refer [dispatch subscribe]]))

(defn better-tab [cm]
  ; based on https://github.com/codemirror/CodeMirror/issues/988#issuecomment-14921785
  (if (.somethingSelected cm)
    (.indentSelection cm "add")
    (.replaceSelection cm
                       (apply str (repeat (.getOption cm "indentUnit") " "))
                       "end"
                       "+input")))

(defn editor-view [code]
  (r/create-class
    {:component-did-mount
     (fn [comp]
       (let [cm (doto 
                  (js/CodeMirror 
                    (r/dom-node comp)
                    (clj->js {:theme "railscasts"
                              :mode "clojure"
                              :extraKeys {"Tab" better-tab}
                              :autofocus true
                              :matchBrackets true
                              :value code}))
                  (.on "change" 
                       (fn [editor]
                         (dispatch [:update-code (.getValue editor)]))))]
         (js/parinferCodeMirror.init cm "smart" #js {:forceBalance true})))

     :reagent-render
     (fn []
       [:div.editor])}))
