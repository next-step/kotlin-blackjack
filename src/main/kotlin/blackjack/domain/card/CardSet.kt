package blackjack.domain.card

class CardSet(private val cards: List<Card>) {
    companion object {
        private val EMPTY_CARD_SET = CardSet(emptyList())

        fun empty(): CardSet {
            return EMPTY_CARD_SET
        }
    }
}
