package blackjack.model

import blackjack.model.card.Card

const val BLACKJACK_MAX_NUMBER = 21

data class Player(val name: String) {
    var cards = mutableListOf<Card>()

    fun drawCard(card: Card) {
        cards.add(card)
    }

    fun continueToTurn(): Boolean {
        return cards.sumBy { it.denomination.point } < BLACKJACK_MAX_NUMBER
    }
}
