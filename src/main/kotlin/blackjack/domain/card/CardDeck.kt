package blackjack.domain.card

class CardDeck(cards: Cards = Cards.getAll()) {
    private val cardDrawer: CardDrawer = CardDrawer(cards)

    init {
        require(cards.list.size == DECK_SIZE) { INCORRECT_DECK_SIZE }
        require(cards.list.toSet().size == DECK_SIZE) { DUPLICATE_CARD }

        cards.shuffle()
    }

    fun drawCard(): Card = cardDrawer.draw()
        ?: throw IllegalArgumentException(EMPTY_DECK)

    companion object {
        private const val DECK_SIZE = 52
        private const val INCORRECT_DECK_SIZE = "카드 덱에는 총 52장이 카드가 있어야 합니다."
        private const val DUPLICATE_CARD = "카드 덱에는 중복되는 카드가 없어야 합니다."
        private const val EMPTY_DECK = "더 이상 카드가 없습니다."
    }
}
