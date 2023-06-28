package blackjack.domain

class Players(private val players: MutableList<Player>, private val blackJackTable: BlackJackTable) {

    private val dealer = Dealer()

    init {
        players.add(DEALER_POSITION, dealer)
    }

    fun getPlayers(): List<Player> {
        return players
    }

    fun giveDefaultCards() {
        players.forEach {
            giveCardsToPlayer(it, START_CARD_COUNT)
        }
    }

    private fun requestGiveMoreCard(viewCallback: GameConditionNotify, player: Player): Boolean {
        val isMoreCard = viewCallback.isNeedMoreCard(player)
        if (isMoreCard) {
            giveCardsToPlayer(player)
        }
        return isMoreCard
    }

    private fun giveCardsToPlayer(player: Player, repeatTime: Int = DEFAULT_CARD_COUNT) {
        repeat(repeatTime) {
            player.addCard(blackJackTable.hitCard())
        }
    }

    fun giveMoreCard(viewCallback: GameConditionNotify) {
        if (dealer.shouldGetMoreCard()) {
            dealer.addCard(blackJackTable.hitCard())
        }

        val playersWithoutDealer = players.filterNot {
            it is Dealer
        }
        playersWithoutDealer.forEach {
            giveMoreCardToPlayer(viewCallback, it)
        }
    }

    private fun giveMoreCardToPlayer(viewCallback: GameConditionNotify, player: Player) {
        do {
            val isMoreCard = requestGiveMoreCard(viewCallback, player)
            viewCallback.giveCardToPlayerDone(player)
        } while (isMoreCard)
    }

    fun judgeGameResult() {
        var dealerScore = ZERO_SCORE

        if (!dealer.isDealerMustLoose()) {
            dealerScore = dealer.getCardScore()
        }

        val playersWithoutDealer = players.filterNot {
            it is Dealer
        }

        playersWithoutDealer.forEach {
            val result = it.matchGameScore(dealerScore)
            setDealerResult(dealer, result)
        }
    }

    private fun setDealerResult(dealer: Dealer, result: GameResultState) {
        with(dealer) {
            val count = resultStateCount[result] ?: DEFAULT_ZERO
            resultStateCount[result] = count.inc()
        }
    }

    companion object {
        private const val DEALER_POSITION = 0
        private const val ZERO_SCORE = 0
        private const val DEFAULT_ZERO = 0
        private const val DEFAULT_CARD_COUNT = 1
        const val START_CARD_COUNT = 2
    }
}
