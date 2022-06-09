package blackjack.domain

const val DEALER = "딜러"

data class BlackJackGame(
    val players: List<Participant>,
    val cardDeck: Deck
) {
    private val playerMap = players.associate { it.name to it.playerCards }
    val normalPlayer = players.filter { it.name != DEALER }
    val dealer = players.first { it.name == DEALER }
    val dealerCards = playerMap[DEALER]!!

    fun firstCardDistribution() {
        players.forEach { participant ->
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
    }

    companion object {
        fun of(players: List<Participant>, deck: Deck): BlackJackGame {
            return BlackJackGame(
                players, deck
            )
        }

        private const val FIRST_DISTRIBUTION_CARD_COUNT = 2
    }
}
