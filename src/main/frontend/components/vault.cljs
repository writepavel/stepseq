(ns frontend.components.vault
  (:require [rum.core :as rum]
            [frontend.ui :as ui]
            [frontend.util :as util]
            [frontend.handler.vault :as vault]
            [frontend.state :as state]
            [frontend.context.i18n :as i18n]))

(rum/defcs syncserver-login <
          (rum/local "" ::syncserver-url)
          [state]
        ;;   (println @state)
          (let [syncserver-url (get state ::syncserver-url)]
            (when-let [current-repo (state/get-current-repo)]
              (rum/with-context [[t] i18n/*tongue-context*]

[:div
 [:h1.title "Login to Sync Server"]
 [:label.font-medium "Sync Server URL: "]
 [:div.mt-4.mb-4.relative.rounded-md.shadow-sm.max-w-xs
  [:input#repo.form-input.block.w-full.sm:text-sm.sm:leading-5
   {:autoFocus true
    :placeholder "http://localhost:8082"
    :on-change (fn [e]
                 (reset! syncserver-url (util/evalue e)))}]]

 (ui/button
  "Matrix SSO Login"
  :on-click #(do (state/set-state! [:me :name] "@pavel:matrix.org")
                 (state/set-state! [:vault :syncserver-authed?] true)))
 [:p.ml-2.opacity-70
          ;; (if (state/github-authed?)
  (if (state/syncserver-authed?)
     (str "Congratulations! You are logged in to Sync Server as " (state/get-name))
    "Press button to log in.")]])))
           )
