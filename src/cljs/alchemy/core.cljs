(ns alchemy.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [alchemy.handlers]
              [alchemy.subs]
              [alchemy.views :as views]))

(defn mount-root []
  (reagent/render [views/main]
                  (.getElementById js/document "app")))

(defn ^:export init [] 
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
