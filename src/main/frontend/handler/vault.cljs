(ns frontend.handler.vault
  (:require [frontend.util :as util :refer-macros [profile]]
            [promesa.core :as p]
            [lambdaisland.glogi :as log]
            [frontend.state :as state]
            [frontend.db :as db]
            [frontend.git :as git]
            [frontend.vault :as vault]
            [frontend.date :as date]
            [frontend.handler.notification :as notification]
            [frontend.handler.route :as route-handler]
            [frontend.handler.common :as common-handler]
            [frontend.config :as config]
            [cljs-time.local :as tl]
            [clojure.string :as string]
            [goog.object :as gobj]))

(defn vault-set-matrix-id!
  [vault-url {:keys [matrix-id-server matrix-username]}]
  (when (and matrix-id-server matrix-username)
    (vault/set-vault-matrix-id
     (config/get-repo-dir vault-url)
     matrix-id-server
     matrix-username)))