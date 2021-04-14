(ns bank-account)

(defn open-account []
  (atom 0))

(defn close-account [account]
  (reset! account nil))

(defn get-balance [account]
  @account)

(defn update-balance [account amount]
  (when @account
    (swap! account + amount)))

(let [account (open-account)
      add10 #(update-balance account 10)]
  (pcalls add10 add10 add10 add10 add10))