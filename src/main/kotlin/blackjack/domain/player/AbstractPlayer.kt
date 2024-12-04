package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.Card.Companion.SpecialNumber

abstract class AbstractPlayer {
    protected var cards = mutableListOf<Card>()

    abstract fun drawCard(newCard: Card)
    abstract fun isBust(): Boolean

    fun calculateCard(): Int {
        val aceCards = cards.filter { it.number == SpecialNumber.A.name }
        var currentSum = cards.sumOf { card -> card.getCardNumber(card.number) }

        aceCards.forEach { _ ->
            if (currentSum + 10 <= 21) currentSum += 10
        }

        return currentSum
    }

    fun getAllCards(): List<Card> {
        return cards.toList()
    }
}
