package blackjack.model

import blackjack.model.card.Card

const val BLACKJACK_MAX_NUMBER = 21

data class Player(val name: String) {
    var cards = mutableListOf<Card>()

    fun drawCard(card: Card) {
        cards.add(card)
    }

    fun getTotalPointForBlackJack(): Int {
        val pointSum = cards.sumBy { it.denomination.point }
        val pointSumOptional = cards.sumBy { it.denomination.pointOptional }

        if (pointSumOptional < BLACKJACK_MAX_NUMBER) return pointSumOptional

        return pointSum
    }

    fun continueToTurn(): Boolean {
        return cards.sumBy { it.denomination.point } < BLACKJACK_MAX_NUMBER
    }
}
