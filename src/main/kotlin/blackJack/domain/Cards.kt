package blackJack.domain

import blackJack.error.ErrorMessage

class Cards(val cards: MutableList<Card>) {
    init {
        require(cards.isNotEmpty()) { ErrorMessage.EMPTY_CARDS.message }
    }

    fun addCard(): Card {
        require(cards.isNotEmpty()) { ErrorMessage.EMPTY_CARDS.message }
        return cards.removeAt(0)
    }

    fun calculateTotalScore(): Int {
        return cards.sumOf { it.rank.score }
    }
}
