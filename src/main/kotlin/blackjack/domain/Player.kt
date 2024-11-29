package blackjack.domain

import blackjack.domain.Card.Companion.SPECIAL_NUMBER

data class Player(val name: String) {
    private var cards = mutableListOf<Card>()

    fun drawCard(newCard: Card) {
        cards.add(newCard)
    }

    fun calculateCard(): Int {
        val aceCards = cards.filter { it.number == SPECIAL_NUMBER.A.name }
        var currentSum = cards.sumOf { card -> card.getCardNumber(card.number) }

        aceCards.forEach { _ ->
            if (currentSum + 10 <= 21) currentSum += 10
        }

        return currentSum
    }

    fun getAllCards(): List<Card> {
        return cards.toList()
    }

    fun isDone(): Boolean = calculateCard() > 21
}