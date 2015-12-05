(ns alchemy.views
    (:require [re-frame.core :as re-frame]))

(defn sandbox []
    (fn []
      [:h1.title "Alchemy"]))
    
(defn selected-item [item] {:class "menu-item menu-item--selected" :on-click #(re-frame/dispatch [:deselect item])})

(defn menu-item-attrs [item]
  (let [base {:class "menu-item" :on-click #(re-frame/dispatch [:select item])}
        selected (re-frame/subscribe [:selected])]
       (if (contains? @selected item) (merge base (selected-item item)) base)))
     
(defn menu-item [item]
  (fn [item]
      [:li (menu-item-attrs item) item]))
    
(defn side-panel []
  (let [available (re-frame/subscribe [:available])
        database (re-frame/subscribe [:database])]
    (fn []
        (let [score (count @available)
              total (+ (count @database) 4)]
        [:div.side-panel
         [:div.score (str score "/" total)]
         [:ul.menu
          (for [item @available] ^{:key item} [menu-item item])]]))))
      
(defn selected []
  (let [selected (re-frame/subscribe [:selected])]
       (fn []
           [:div.selected-panel
            [:button.transmogrify {:on-click #(re-frame/dispatch [:transmogrify])} "Transmogrify"]
            (for [item @selected] ^{:key item} [:div item])])))
          
(defn notification [message]
  (fn [message]
      [:div.notification message]))
    
(defn notify-match []
  (let [last-matched (re-frame/subscribe [:last-matched])]
       (fn []
           (and @last-matched [notification (str "You discovered " @last-matched "!")]))))
      
(defn main []
  (fn []
      [:main
       [sandbox]
       [:section.panel-container
        [side-panel]
        [selected]]
       [:footer [notify-match]]]))