(ns alchemy.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/register-sub
 :available
 (fn [db]
   (reaction (:available @db))))

(re-frame/register-sub
 :database
 (fn [db]
   (reaction (:database @db))))

(re-frame/register-sub
 :selected
 (fn [db]
   (reaction (:selected @db))))

(re-frame/register-sub
 :last-matched
 (fn [db]
   (reaction (:last-matched @db))))