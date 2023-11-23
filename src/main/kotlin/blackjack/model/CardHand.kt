package blackjack.model

class CardHand(cards: List<Card>) {
    var cards: List<Card> = cards
        private set

    fun addCard(card: Card) {
        val newCards = cards.toMutableList()
        newCards.add(card)
        cards = newCards.toList()
    }

    fun getTotalScore(): Int = getCardValues().fold(0) { total, it ->
        total + it.getScore(total)
    }

    fun isSameOrSmallerThanBlackJack(): Boolean = getTotalScore() <= BLACKJACK

    private fun getCardValues(): List<CardValue> =
        cards.map { it.cardValue }.sortedWith(compareBy { if (it == CardValue.A) Int.MAX_VALUE else 0 })


    companion object {
        const val BLACKJACK = 21
    }
}
