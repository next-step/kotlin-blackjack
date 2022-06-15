package blackjack.domain

import blackjack.domain.enums.CardPoint

class Player(val name: String) {
    val cards = mutableListOf<Card>()
    fun takeCard(card: Card) {
        cards.add(card)
    }

    fun needCard(answer: String): Boolean =
        when (answer) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException("플레이어 상태가 이상합니다")
        }

    fun score(): Int {
        var result = 0
        cards.map { card -> result += calculateScore(card, result) }

        return result
    }

    private fun calculateScore(card: Card, result: Int): Int =
        if (card.point.max + result <= CardPoint.BLACK_JACK_SCORE) card.point.max else card.point.min
}
