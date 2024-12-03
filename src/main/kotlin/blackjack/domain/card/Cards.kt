package blackjack.domain.card

import blackjack.domain.score.Score

class Cards(
    private val cards: MutableList<Card> = mutableListOf(),
) {
    fun add(card: Card) {
        cards.add(card)
    }

    fun getCards(): List<Card> = cards

    fun getScore(): Score {
        val numbers = cards.map { it.number }
        return Score.calculate(numbers, isContainAce(numbers))
    }

    private fun isContainAce(numbers: List<CardNumber>): Boolean = numbers.any { it == CardNumber.Ace }
}
