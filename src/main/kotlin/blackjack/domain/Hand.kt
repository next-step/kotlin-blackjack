package blackjack.domain

class Hand {
    val cards: MutableList<Card> = mutableListOf()
    fun add(card: Card) {
        if (bust()) {
            throw RuntimeException()
        }

        cards.add(card)
    }

    fun total(): Int {
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

    fun bust(): Boolean = hardTotal() > BLACKJACK

    companion object {
        fun create(openCards: OpenCards): Hand {
            val hand = Hand()
            hand.add(openCards.first)
            hand.add(openCards.second)
            return hand
        }
    }
}
