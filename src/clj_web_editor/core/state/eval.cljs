(ns clj-web-editor.core.state.eval
  (:require
    [replumb.core :as replumb]))

(defn eval-fx [[code callback]]
  (replumb/read-eval-call 
    {:target :browser
     :context :statement
     :preloads {:require '#{reagent.core :as r}}
     :load-fn! (fn [{:keys [name macros path]} cb]
                 (cb {:lang :clj
                      :source path}))}
    callback
    code))

