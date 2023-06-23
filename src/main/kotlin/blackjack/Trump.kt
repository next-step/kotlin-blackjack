package blackjack

class Trump {
    private val _cards = mutableListOf<Card>().apply {
        addAll(SPADES)
        addAll(CLOVERS)
        addAll(DIAMONDS)
        addAll(HEARTS)
    }
    val cards: List<Card> get() = _cards

    fun getCard(): Card {
        val card = _cards.randomOrNull() ?: throw IllegalStateException("더 이상 나누어줄 카드가 없습니다")
        _cards.remove(card)
        return card
    }

    companion object {
        private val SPADES = Card.VALUES.map { Card.from(CardType.SPADE, it) }
        private val CLOVERS = Card.VALUES.map { Card.from(CardType.CLOVER, it) }
        private val DIAMONDS = Card.VALUES.map { Card.from(CardType.DIAMOND, it) }
        private val HEARTS = Card.VALUES.map { Card.from(CardType.HEART, it) }
    }
}
