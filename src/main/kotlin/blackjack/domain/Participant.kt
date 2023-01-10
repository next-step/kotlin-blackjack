package blackjack.domain

import blackjack.common.Policy

abstract class Participant(
    val name: String,
    val cards: Cards = Cards(),
) {
    fun hit(card: Card) {
        cards.add(card)
    }

    abstract fun canHit(): Boolean

    internal fun isBust(): Boolean {
        return cards.getScore() > Policy.BUST_SCORE
    }

    internal fun isBlackJack(): Boolean {
        return cards.getScore() == Policy.BUST_SCORE && cards.size == Policy.INITIAL_CARD_COUNT
    }

    internal fun isMaxScore(): Boolean {
        return cards.getScore() == Policy.BUST_SCORE
    }

    fun getScore(): Int {
        return cards.getScore()
    }
}
