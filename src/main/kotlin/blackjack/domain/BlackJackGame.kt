package blackjack.domain

data class BlackJackGame(
    private val dealer: Dealer,
    private val players: List<Participant>,
    private val cardDeck: Deck
) {

    val participants = listOf(dealer) + players
    val participantsSortByPlayer = players + listOf(dealer)
    private val playerMap = participants.associate { it.name to it.playerCards }

    fun firstCardDistribution() {
        participants.forEach { participant ->
            participant.addFirstCard()
        }
    }

    fun drawTo(playerName: String) {
        playerMap[playerName]!!.addCard(cardDeck.draw())
    }

    fun match() {
        val gameResult = GameResult(dealer, players)
        if (dealer.isBust()) {
            gameResult.setDealerIsWin()
        } else {
            gameResult.decideWinner()
        }
    }

    private fun Participant.addFirstCard() {
        repeat(FIRST_DISTRIBUTION_CARD_COUNT) {
            addCard(cardDeck.draw())
        }
    }

    companion object {
        fun of(dealer: Dealer, players: List<Participant>, deck: Deck): BlackJackGame {
            return BlackJackGame(
                dealer, players, deck
            )
        }

        private const val FIRST_DISTRIBUTION_CARD_COUNT = 2
    }
}
