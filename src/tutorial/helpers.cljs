(ns tutorial.helpers
 (:require
   [cljs.reader :as reader]
   [zprint.core :refer [zprint-str]])
 (:import 
   goog.net.XhrIo))

(defn pprint [code]
  (println (zprint-str code 
                       100 
                       {:style :community
                        :parse-string? false
                        :map {:comma? false
                              :force-nl? true}})))
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

(def read-string reader/read-string)
