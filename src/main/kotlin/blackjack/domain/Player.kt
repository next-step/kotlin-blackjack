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

    private fun calculateScore(card: Card, result: Int): Int {
        if (card.point === CardPoint.ACE) {
            return chooseAceScore(result)
        }

        return card.point.score
    }

    private fun chooseAceScore(result: Int): Int {
        if (CardPoint.ACE_SPECIAL_VALUE + result <= CardPoint.BLACK_JACK_SCORE) {
            return CardPoint.ACE_SPECIAL_VALUE
        }

        return CardPoint.ACE.score
    }
}
