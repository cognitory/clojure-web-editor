(ns clj-web-editor.core.state.eval
  (:require
    [replumb.core :as replumb]))

(defn eval-fx [{:keys [code 
                       on-success on-error 
                       on-print on-warning]}]

  ; 'binding' does not work here when external files 
  ; are fetched prior to running the code
  ; just set *print-fn* permanently  
  (set! *print-fn* (fn [& args]
                     (apply on-print args))) 

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

