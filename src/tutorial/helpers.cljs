(ns tutorial.helpers
 (:require
   [cljs.reader :as reader])
 (:import 
   goog.net.XhrIo))

(defn xhr
  [file-url src-cb]
  (try
    (.send XhrIo file-url
           (fn [e]
             (if (.isSuccess (.-target e))
               (src-cb (.. e -target getResponseText))
               (src-cb nil))))
    (catch :default e
      (src-cb nil))))

(defn get-data [callback]
  (xhr "data.edn"
       (fn [data]
         (callback (reader/read-string data)))))
