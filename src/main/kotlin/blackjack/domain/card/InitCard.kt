package blackjack.domain.card

@JvmInline
value class InitCard(val cards: Cards) {

    init {
        require(cards.size == INIT_CARD_SIZE) {
            "only support two cards"
        }
    }

    companion object {

        const val INIT_CARD_SIZE = 2

        fun create(cards: List<Card>): InitCard {
            return InitCard(Cards(cards))
        }
    }
}
