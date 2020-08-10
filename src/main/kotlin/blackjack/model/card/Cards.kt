package blackjack.model.card

import blackjack.model.player.BLACKJACK_MAX_NUMBER

const val BOTTOM_CARD_NUMBER = 0

data class Cards(val cards: MutableList<Card>) {
    fun getCard(): Card {
        cards.removeAt(BOTTOM_CARD_NUMBER)
        return cards[BOTTOM_CARD_NUMBER]
    }

    fun add(card: Card) {
        cards.add(card)
    }

    fun sumByPoint(): Int {
        return cards.sumBy { it.denomination.point }
    }

    private fun sumByOptionalPoint(): Int {
        return cards.sumBy { it.denomination.pointOptional }
    }

    fun getDisplayCards(): String {
        return cards.joinToString(separator = ",") { "${it.denomination.title}${it.suit.title}" }
    }

    fun getTotalPointForBlackJack(): Int {
        val pointSum = sumByPoint()
        val pointSumOptional = sumByOptionalPoint()

        if (pointSumOptional < BLACKJACK_MAX_NUMBER) return pointSumOptional

        return pointSum
    }
}
