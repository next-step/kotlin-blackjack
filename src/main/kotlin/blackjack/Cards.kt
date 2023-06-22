package blackjack

class Cards {
    val items: List<Card> = (SPADES + CLOVERS + DIAMONDS + HEARTS).shuffled()

    companion object {
        private val SPADES = Card.VALUES.map { Card.from(CardType.SPADE, it) }
        private val CLOVERS = Card.VALUES.map { Card.from(CardType.CLOVER, it) }
        private val DIAMONDS = Card.VALUES.map { Card.from(CardType.DIAMOND, it) }
        private val HEARTS = Card.VALUES.map { Card.from(CardType.HEART, it) }
    }
}
