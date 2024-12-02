package blackjack.domain

class Game(
    val players: Players,
    val deck: Deck,
) {
    val isDone: Boolean
        get() = players.isDone

    fun initialDeal() {
        repeat(INITIAL_ROUNDS) {
            players.dealRoundOfCardsFrom(deck)
        }
    }

    companion object {
        private const val INITIAL_ROUNDS = 2
    }
}
