package blackjack.domain

import blackjack.model.Card

class Game(val players: Players, val dealer: Dealer) {
    init {
        dealer.shuffle()
        deliverInitialCards()
    }

    private fun deliverInitialCards() {
        players.value.forEach {
            val cards = mutableListOf<Card>().apply {
                repeat(INITIAL_CARDS_COUNT) {
                    add(dealer.deliverCard())
                }
            }
            it.readyToPlay(cards)
        }
    }

    companion object {
        const val INITIAL_CARDS_COUNT = 2
    }
}
