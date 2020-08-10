package blackjack.model.card

const val BOTTOM_CARD_NUMBER = 0

data class Cards(val cards: MutableList<Card>) {
    fun getCard(): Card {
        cards.removeAt(BOTTOM_CARD_NUMBER)
        return cards[BOTTOM_CARD_NUMBER]
    }
}
