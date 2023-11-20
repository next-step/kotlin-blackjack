package blackjack.domain.card

class CardSet(val cards: List<Card>) {

    fun addCard(card: Card): CardSet {
        val newCards = cards.toMutableList()
        newCards.add(card)
        return CardSet(newCards)
    }

    companion object {
        private val EMPTY_CARD_SET = CardSet(emptyList())

        fun empty(): CardSet {
            return EMPTY_CARD_SET
        }
    }
}
