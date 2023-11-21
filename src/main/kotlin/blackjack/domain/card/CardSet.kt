package blackjack.domain.card

class CardSet(val cards: List<Card>) {
    init {
        val distinct = cards.distinct()
        require(distinct.size == cards.size) { "카드뭉치에는 중복된 카드를 허용하지 않습니다." }

    }

    fun addCard(card: Card): CardSet {
        val newCards = cards.toMutableList()
        newCards.add(card)
        return CardSet(newCards)
    }

    companion object {
        private val EMPTY_CARD_SET = CardSet(emptyList())

        fun of(vararg card: Card): CardSet {
            return CardSet(card.toList())
        }

        fun empty(): CardSet {
            return EMPTY_CARD_SET
        }
    }
}
