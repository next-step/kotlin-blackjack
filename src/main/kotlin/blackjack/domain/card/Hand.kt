package blackjack.domain.card

import blackjack.domain.player.OpenCards

class Hand {
    private val cards: MutableList<Card> = mutableListOf()

    fun getCards(): List<Card> = cards.toList()
    fun first(): Card = cards.firstOrNull() ?: throw IllegalStateException()
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

    fun blackjack(): Boolean = softTotal() == BLACKJACK && cards.size == 2

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

        fun empty(): Hand = Hand()
    }
}
