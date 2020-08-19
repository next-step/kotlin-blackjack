package blackjack

class Dealer(cards: Cards) : Gamer(NAME, cards) {
    fun addCards(card: Card) {
        if (isLessThanScore()) {
            cards.addCard(card)
        }
    }

    fun getDealerScore(): Int {
        return cards.sumCardNumbers()
    }

    private fun isLessThanScore(): Boolean {
        return cards.sumCardNumbers() <= DEALER_STANDARD_SCORE
    }

    companion object {
        const val DEALER_STANDARD_SCORE = 16
        const val NAME = "딜러"
    }
}
