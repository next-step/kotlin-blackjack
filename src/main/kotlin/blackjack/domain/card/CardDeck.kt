package blackjack.domain.card

class CardDeck {
    private val cards: Cards = Cards.getAll()
    private val cardDrawer: CardDrawer = CardDrawer(cards)

    init {
        cards.shuffle()
    }

    fun drawCard(): Card = cardDrawer.draw()
        ?: throw IllegalArgumentException(EMPTY_DECK)

    companion object {
        private const val EMPTY_DECK = "더 이상 카드가 없습니다."
    }
}
