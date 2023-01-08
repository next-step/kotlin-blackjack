package blackjack.domain

import blackjack.common.Policy

class Participant(
    val name: String,
    val cards: Cards = Cards(),
) {
    fun hit(card: Card) {
        cards.add(card)
    }

    fun canHit(): Boolean {
        return !(isBust() || isBlackJack() || isMaxScore())
    }

    private fun isBust(): Boolean {
        return cards.getScore() > Policy.BUST_SCORE
    }

    private fun isBlackJack(): Boolean {
        return cards.getScore() == Policy.BUST_SCORE && cards.size == Policy.INITIAL_CARD_COUNT
    }

    private fun isMaxScore(): Boolean {
        return cards.getScore() == Policy.BUST_SCORE
    }

    fun getScore(): Int {
        return cards.getScore()
    }
}
