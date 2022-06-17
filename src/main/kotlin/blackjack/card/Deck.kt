package blackjack.card

class Deck(private val cards: Cards) {
    init {
        require(cards.size == DECK_INIT_CARD_SIZE) {
            "덱은 ${DECK_INIT_CARD_SIZE}장의 카드로 구성 돼 있습니다."
        }
    }

    fun draw(): Card {
        return cards.drawCard()
    }

    companion object {
        private const val DECK_INIT_CARD_SIZE = 52
    }
}
