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
        isDealerBlackJack: Boolean,
        dealerCardSum: Int,
    ): PlayerToProfitMoney {
        return PlayerToProfitMoney(
            value.associateWith { player ->
                val gameResult =
                    GameResult.getGameResultsWith(
                        isPlayerBlackJackInitially = player.isBlackJackInitially,
                        isDealerBlackJackInitially = isDealerBlackJack,
                        dealerCardSum = dealerCardSum,
                        playerCardSum = player.cardsSum,
                    )
                player.getProfitMoney(gameResult)
            },
        )
    }
}
