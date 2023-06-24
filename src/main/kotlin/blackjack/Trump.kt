package blackjack

class Trump(private val _cards: MutableList<Card> = defaultTrump()) {
    val cards: List<Card> get() = _cards.toList()

    fun getCard(): Card = _cards.removeLastOrNull() ?: throw IllegalStateException("더 이상 나누어줄 카드가 없습니다")

    companion object {
        private val SPADES = CardValue.values().map { Card.from(CardType.SPADE, it) }
        private val CLOVERS = CardValue.values().map { Card.from(CardType.CLOVER, it) }
        private val DIAMONDS = CardValue.values().map { Card.from(CardType.DIAMOND, it) }
        private val HEARTS = CardValue.values().map { Card.from(CardType.HEART, it) }

        fun defaultTrump(): MutableList<Card> = mutableListOf<Card>().apply {
            addAll(SPADES)
            addAll(CLOVERS)
            addAll(DIAMONDS)
            addAll(HEARTS)
        }
    }
}
