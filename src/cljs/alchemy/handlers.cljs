(ns alchemy.handlers
    (:require [re-frame.core :as re-frame]
              [alchemy.db :as db]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/register-handler
 :select
 (fn [db [_ item]]
     (let [selected (conj (:selected db) item)]
       (if (<= (count selected) 4) (merge db {:selected selected}) db))))
   
(re-frame/register-handler
 :deselect
 (fn [db [_ item]]
     (let [selected (disj (:selected db) item)]
       (merge db {:selected selected}))))
    
        
(re-frame/register-handler
 :transmogrify
 (fn [db _]
    (let [selected (:selected db)
          matched (first (first (filter #(= (last %) selected) (:database db))))
          available (merge (:available db) matched)]
        (if (seq matched) (merge db {:available available :last-matched matched :selected #{}}) db))))
         