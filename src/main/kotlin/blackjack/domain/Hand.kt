package blackjack.domain

import java.lang.RuntimeException

class Hand {
    val cards: MutableList<Card> = mutableListOf()
    fun add(card: Card) {
        if (burst()) {
            throw RuntimeException()
        }

        cards.add(card)
    }

    fun bestHandTotal(): Int {
        if (!hasByCardNumber(CardNumber.ACE)) {
            return hardTotal()
        }

        return listOf(hardTotal(), softTotal())
            .filter { it <= BLACKJACK }
            .max()
    }

    private fun hardTotal(): Int {
        return cards.sumOf { it.cardNumber.value }
    }

    private fun softTotal(): Int {
        return hardTotal() + ACE_SOFT
    }

    private fun hasByCardNumber(cardNumber: CardNumber): Boolean = cards.any { it.cardNumber == cardNumber }

    fun burst(): Boolean = hardTotal() > BLACKJACK

    companion object {
        fun create(openCards: OpenCards): Hand {
            val hand = Hand()
            hand.add(openCards.first)
            hand.add(openCards.second)
            return hand
        }
    }
}
