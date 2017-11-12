(ns clj-web-editor.core.state.eval
  (:require
    [replumb.core :as replumb]
    ; require this ns so it is available for the tutorial
    [tutorial.helpers :as h]))

(defn eval-fx [{:keys [code 
                       on-success on-error 
                       on-print on-warning]}]

  (replumb/read-eval-call 
    {:target :browser
     :context :statement
     :no-pr-str-on-value true
     :load-fn! (fn [{:keys [name macros path]} cb]
                 (cb {:lang :clj :source ""}))} 
    (fn [result]
      (when (result :warning)
        (on-warning (result :warning)))
      (if (result :success?)
        (on-success (result :value))
        (on-error (result :error))))

    code))

