(ns alchemy.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/register-sub
 :available
 (fn [db]
   (reaction (:available @db))))