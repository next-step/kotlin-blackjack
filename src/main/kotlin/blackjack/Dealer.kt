package blackjack

class Dealer(cards: Cards) : Gamer(NAME, cards) {
    fun addCards(card: Card) {
        if (cards.sumCardNumbers() <= DEALER_STANDARD_SCORE) {
            cards.addCard(card)
        }
    }

    companion object {
        const val DEALER_STANDARD_SCORE = 16
        const val NAME = "딜러"
    }
}
