package blackjack.model

class CardHand(cards: List<Card>) {
    var cards: List<Card> = cards
        private set
    val totalScore: Int
        get() = getTotalHandScore()
    val isSameOrSmallerThanBlackJack: Boolean
        get() = getIsSameOrSmallerThanBlackJack()

    fun addCard(card: Card) {
        val newCards = cards.toMutableList()
        newCards.add(card)
        cards = newCards.toList()
    }

    private fun getTotalHandScore(): Int = getCardValues().fold(0) { total, it ->
        total + it.getScore(total)
    }

    private fun getIsSameOrSmallerThanBlackJack(): Boolean = getTotalHandScore() <= BLACKJACK

    private fun getCardValues(): List<CardValue> =
        cards.map { it.cardValue }.sortedWith(compareBy { if (it == CardValue.A) Int.MAX_VALUE else 0 })


    companion object {
        const val BLACKJACK = 21
    }
}
