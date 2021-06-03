(ns frontend.vault
  (:refer-clojure :exclude [clone merge])
  (:require [promesa.core :as p]
            [frontend.util :as util]
            [frontend.config :as config]
            [clojure.string :as string]
            [clojure.set :as set]
            [frontend.state :as state]
            [cljs-bean.core :as bean]))

(defn set-vault-matrix-id
  [dir matrix-id-server matrix-username]
  (-> (p/let [_ (js/window.workerThread.setConfig dir "matrix.idserver" matrix-id-server)]
        (js/window.workerThread.setConfig dir "matrix.username" matrix-username))
      (p/catch (fn [error]
                 (prn "Sync Server set config error:" error)))))
