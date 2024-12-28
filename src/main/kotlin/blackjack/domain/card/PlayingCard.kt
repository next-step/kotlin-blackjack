package blackjack.domain.card

class PlayingCard(
    private val suit: Suit,
    private val denomination: Denomination,
) {
    val isAce: Boolean = denomination.isAce
    val score: Int = denomination.score

    override fun toString(): String = "${denomination.score}${suit.symbol}"

    companion object {
        private val CARDS: Map<String, PlayingCard> = createAllCards()

        private fun createAllCards(): Map<String, PlayingCard> {
            return Suit.entries
                .flatMap { it.createCards() }
                .associateBy { toKey(it.suit, it.denomination) }
        }

        fun createDeck(): List<PlayingCard> = CARDS.values.toList()

        fun of(
            suit: Suit,
            denomination: Denomination,
        ): PlayingCard {
            return CARDS[toKey(suit, denomination)]
                ?: throw NoSuchElementException("해당하는 카드가 없습니다.")
        }

        private fun toKey(
            suit: Suit,
            denomination: Denomination,
        ): String {
            return suit.name + denomination.name
        }
    }
}
