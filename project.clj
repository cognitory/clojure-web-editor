(defproject clj-web-editor "0.0.1"
  
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [replumb "0.2.4"]
                 [cljsjs/codemirror "5.31.0-0"]
                 [cljsjs/parinfer "3.11.0-0"]
                 [cljsjs/parinfer-codemirror "1.4.1-2"]
                 [re-frame "0.10.2"]
                 [reagent "0.8.0-alpha2"]
                 [garden "1.3.3"]
                 [com.cognitect/transit-cljs "0.8.239"]]

  :plugins [[lein-figwheel "0.5.14"]
            [lein-cljsbuild "1.1.7"]]
  
  :figwheel {:server-port 6434}

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src"]
                        :figwheel {:on-jsload "clj-web-editor.core.core/reload"}
                        :compiler {:main "clj-web-editor.core.core"
                                   :asset-path "/js/out/dev"
                                   :output-to "resources/public/js/editor.js"
                                   :output-dir "resources/public/js/out/dev"}}
                       {:id "release"
                        :source-paths ["src"]
                        :compiler {:main "clj-web-editor.core.core"
                                   :asset-path "/js/out"
                                   :output-to "resources/public/js/editor.min.js"
                                   :output-dir "resources/public/js/out/prod"
                                   :optimizations :simple
                                   :pretty-print false
                                   ; to debug advanced compilation issues, enable these options:
                                   ;:source-map "resources/public/js/editor.min.js.map"
                                   ;:pseudo-names true
                                   }}]})
