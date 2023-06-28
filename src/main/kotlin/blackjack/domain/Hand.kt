package blackjack.domain

class Hand {
    val cards: MutableList<Card> = mutableListOf()
    var hasAce: Boolean = false
    fun add(card: Card) {
        if (card.cardNumber == CardNumber.ACE) {
            hasAce = true
        }
        cards.add(card)
    }
    fun total(): Int {
        return cards.sumOf { it.cardNumber.value }
    }
    companion object {
        fun create(pairOfCard: Pair<Card, Card>): Hand {
            val hand = Hand()
            pairOfCard.toList().forEach { hand.add(it) }
            return hand
        }
    }
}
