(ns clj-web-editor.core.state.eval
  (:require
    [replumb.core :as replumb]))

(defn eval-fx [{:keys [code 
                       on-success on-error 
                       on-print on-warning]}]
  (binding [*print-fn* (fn [& args]
                         (apply on-print args))]
    (replumb/read-eval-call 
      {:target :browser
       :context :statement
       :no-pr-str-on-value true
       :preloads {:require '#{reagent.core :as r}}
       :load-fn! (fn [{:keys [name macros path]} cb]
                   (cb {:lang :clj
                        :source path}))}
      (fn [result]
        (when (result :warning)
          (on-warning (result :warning)))
        (if (result :success?)
          (on-success (result :value))
          (on-error (result :error))))
      code)))

