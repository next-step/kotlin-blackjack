package blackjack.domain

class Game(
    val players: Players,
    val deck: Deck,
) {
    val isDone: Boolean
        get() = players.isDone
    val currentPlayer: Player
        get() = players.currentPlayer

    fun initialDeal() {
        repeat(INITIAL_ROUNDS) {
            players.dealRoundOfCardsFrom(deck)
        }
    }

    fun playerHits() {
        players.hit(deck)
    }

    fun playerStands() {
        players.stand()
    }

    companion object {
        private const val INITIAL_ROUNDS = 2
    }
}
