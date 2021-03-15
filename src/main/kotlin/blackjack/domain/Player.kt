package blackjack.domain

class Player(val name: String) {
    private val cards: Cards = Cards(arrayListOf<Card>())

    init {
        repeat(INIT_CARD_NUMBER) {
            cards.drawCard()
        }
    }

    fun drawCard() {
        cards.drawCard()
    }

    fun calculateCards(): Int {
        return cards.calculateCards()
    }

    fun getCardList(): List<Card> {
        return cards.toList()
    }

    companion object {
        private const val INIT_CARD_NUMBER = 2
    }
}
