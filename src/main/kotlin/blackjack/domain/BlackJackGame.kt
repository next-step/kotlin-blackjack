package blackjack.domain

data class BlackJackGame(
    val players: List<Participant>,
    val cardDeck: Deck
) {
    private val playerMap = players.associate { it.name to it.playerCards }
    fun firstCardDistribution() {
        players.forEach { participant ->
            participant.addFirstCard()
        }
    }

    fun drawTo(player: Participant) {
        playerMap[player.name]!!.addCard(cardDeck.draw())
    }

    private fun Participant.addFirstCard() {
        repeat(FIRST_DISTRIBUTION_CARD_COUNT) {
            addCard(cardDeck.draw())
        }
    }

    companion object {
        fun of(playerNames: List<String>, deck: Deck): BlackJackGame {
            return BlackJackGame(
                playerNames.map { Participant(it) }, deck
            )
        }

        private const val FIRST_DISTRIBUTION_CARD_COUNT = 2
    }
}
