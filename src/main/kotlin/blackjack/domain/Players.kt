package blackjack.domain

import blackjack.view.GameConditionNotify

class Players(private val players: MutableList<Player>, private val blackJackTable: BlackJackTable) {

    init {
        players.add(DEALER_POSITION, Dealer())
    }

    fun getPlayers(): List<Player> {
        return players
    }
    private fun giveCardsToPlayer(player: Player, repeatTime: Int = DEFAULT_CARD_COUNT) {
        repeat(repeatTime) {
            player.addCard(blackJackTable.hitCard())
        }
    }

    fun giveDefaultCards() {
        players.forEach {
            giveCardsToPlayer(it, START_CARD_COUNT)
        }
    }

    private fun inputMoreCard(viewCallback: GameConditionNotify, it: Player): Boolean {
        val isMoreCard = viewCallback.isNeedMoreCard(it)
        if (isMoreCard) {
            giveCardsToPlayer(it)
        }
        return isMoreCard
    }

    private fun giveMoreCard(viewCallback: GameConditionNotify, player: Player) {
        do {
            val isMoreCard = inputMoreCard(viewCallback, player)
            viewCallback.showPlayerCards(player)
        } while (isMoreCard)
    }

    fun giveMoreCard(viewCallback: GameConditionNotify) {
        players.forEach {
            if (isDealer(it)) return@forEach
            giveMoreCard(viewCallback, it)
        }
    }

    private fun isDealer(player: Player): Boolean {
        if (player is Dealer) {
            checkDealerGetMoreCard(player)
            return true
        }
        return false
    }

    private fun checkDealerGetMoreCard(dealer: Dealer) {
        if (dealer.shouldGetMoreCard()) {
            dealer.addCard(blackJackTable.hitCard())
        }
    }

    fun judgeGameResult() {
        val dealer = players[DEALER_POSITION] as Dealer
        var dealerScore = ZERO_SCORE

        if (!dealer.isDealerMustLoose()) {
            dealerScore = dealer.getCardScore()
        }

        val playersWithoutDealer = players.filterNot {
            it is Dealer
        }

        playersWithoutDealer.forEach {
            val result = setGameScoreForPlayer(dealerScore, it)
            setDealerResult(dealer, result)
        }
    }

    private fun setDealerResult(dealer: Dealer, result: GameResultState) {
        with(dealer) {
            val count = resultStateCount[result] ?: DEFAULT_ZERO
            resultStateCount[result] = count.inc()
        }
    }

    private fun setGameScoreForPlayer(dealerScore: Int, player: Player): GameResultState {
        var gameResult = GameResultState.DRAW
        if (dealerScore > player.getCardScore()) {
            gameResult = GameResultState.LOSE
        }
        if (dealerScore < player.getCardScore()) {
            gameResult = GameResultState.WIN
        }
        player.setResultState(gameResult)
        return gameResult
    }

    companion object {
        private const val DEALER_POSITION = 0
        private const val ZERO_SCORE = 0
        private const val DEFAULT_ZERO = 0
        private const val DEFAULT_CARD_COUNT = 1
        const val START_CARD_COUNT = 2
    }
}
