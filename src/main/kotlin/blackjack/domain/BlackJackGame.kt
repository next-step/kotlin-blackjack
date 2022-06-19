package blackjack.domain

data class BlackJackGame(
    val dealer: Dealer,
    val players: List<Player>,
    private val cardDeck: Deck
) {

    val participants = listOf(dealer) + players
    val participantsSortByPlayer = players + listOf(dealer)
    private val playerMap = participants.associateBy { it.name }

    fun firstCardDistribution() {
        participants.forEach { participant ->
            participant.addFirstCard()
        }
    }

    fun drawTo(playerName: String) {
        playerMap[playerName]!!.addCard(cardDeck.draw())
    }

    private fun Participant.addFirstCard() {
        repeat(FIRST_DISTRIBUTION_CARD_COUNT) {
            addCard(cardDeck.draw())
        }
        setFirstDistributionBlackJack()
    }

    companion object {
        fun of(dealer: Dealer, players: List<Player>, deck: Deck): BlackJackGame {
            return BlackJackGame(
                dealer, players, deck
            )
        }

        private const val FIRST_DISTRIBUTION_CARD_COUNT = 2
    }
}
