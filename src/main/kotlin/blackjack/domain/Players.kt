package blackjack.domain

data class Players(val value: List<Player>) {
    fun onEachPreparePlay(action: (Player) -> Unit): Players {
        onEach(action)
        return this
    }

    fun onEachStartPlay(action: (Player) -> Unit): Players {
        onEach(action)
        return this
    }

    fun onEach(action: (Player) -> Unit): Players {
        value.forEach { action(it) }
        return this
    }

    fun getPlayersToProfitMoney(dealer: Dealer): PlayerToProfitMoney {
        val map =
            value.associateWith { player ->
                val gameResult = player.getGameResultWith(dealer)
                player.setProfitMoneyFromGameResult(gameResult)

                val playerProfitMoney = player.profitMoney
                dealer.adjustProfit(playerProfitMoney)
                playerProfitMoney
            }
        return PlayerToProfitMoney(map)
    }
}
