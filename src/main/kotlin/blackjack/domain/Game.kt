package blackjack.domain

class Game(val players: Players, val dealer: Dealer) {
    companion object {
        const val INITIAL_CARDS_COUNT = 2
    }
}
