package blackjack.domain

class Game(val players: Players, val dealer: Dealer) {
    init {
        dealer.shuffle()
        deliverInitialCards()
    }

    private fun deliverInitialCards() {
        players.value.forEach {
            List(INITIAL_CARDS_COUNT) { dealer.deliverCard() }
                .let(it::readyToPlay)
        }
    }

    companion object {
        const val INITIAL_CARDS_COUNT = 2
    }
}
