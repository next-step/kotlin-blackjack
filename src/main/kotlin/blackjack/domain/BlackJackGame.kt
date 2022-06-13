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

    fun match(): GameResult {
        val gameResult = GameResult(dealer, players)
        players.forEach {
            matchWithPlayer(gameResult, it, dealer)
        }
        return gameResult
    }

    private fun matchWithPlayer(gameResult: GameResult, participant: Participant, dealer: Dealer) {
        when {
            participant.isBust() -> gameResult.playerIsBust(participant)
            dealer.isBust() -> gameResult.dealerIsBust(participant)
            else -> gameResult.decideWinner(participant)
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
