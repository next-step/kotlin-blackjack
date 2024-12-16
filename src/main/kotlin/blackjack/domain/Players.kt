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

    fun getPlayersToProfitMoney(
        getGameResult: (Player) -> GameResult,
        onSetPlayerProfitMoney: (ProfitMoney) -> Unit
    ): PlayerToProfitMoney {
        val map =
            value.associateWith { player ->
                val gameResult = getGameResult(player)
                player.setProfitMoneyFromGameResult(gameResult)
                onSetPlayerProfitMoney(player.profitMoney)
                player.profitMoney
            }
        return PlayerToProfitMoney(map)
    }
}
