package blackjack.domain

import blackjack.common.Policy

class Participant(
    val name: String,
    val cards: Cards = Cards(),
    var score: Int = 0,
) {
    fun addCard(card: Card) {
        cards.add(card)
        score += card.denomination.calc(score)
    }

    fun isBust(): Boolean {
        return score > Policy.BUST_SCORE
    }

    fun isBlackJack(): Boolean {
        return score == Policy.BUST_SCORE && cards.cards.size == 2
    }

    fun isHit(): Boolean {
        return score < 16
    }
}
