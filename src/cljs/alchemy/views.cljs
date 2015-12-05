(ns alchemy.views
    (:require [re-frame.core :as re-frame]))

(defn sandbox []
    (fn []
      [:h1.title "Alchemy"]))
    
(defn side-panel []
  (let [available (re-frame/subscribe [:available])]
    (fn []
        [:div.side-panel
         [:ul.menu
          (for [item @available] ^{:key (:name item)} [:li.menu-item (:name item)])]
        
        ])))
      
(defn main []
  (fn []
      [:div
       [sandbox]
       [side-panel]]))