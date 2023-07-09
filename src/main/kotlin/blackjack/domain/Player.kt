package blackjack.domain

import blackjack.domain.card.Cards

data class Player(
    val name: String,
    val cards: Cards = Cards()
) {

    override fun toString(): String {
        return name
    }

    fun getScore(): Int {
        return cards.getScore()
    }
}
