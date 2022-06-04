package blackjack.domain

data class BlackJackGame(
    val players: List<Participant>,
    private val cardDeck: Deck
) {
    fun firstCardDistribution() {
        players.forEach { participant ->
            repeat(FIRST_DISTRIBUTION_CARD_COUNT) { participant.addCard() }
        }
    }

    companion object {
        fun of(playerNames: List<String>, cardDeck: Deck): BlackJackGame {
            return BlackJackGame(
                playerNames.map { Participant.of(it, cardDeck) }, cardDeck
            )
        }

        private const val FIRST_DISTRIBUTION_CARD_COUNT = 2
    }
}
