package blackjack.domain

const val BLACKJACK_POINT = 21

open class Player(val name: String) {
    var cards: List<Card> = emptyList()
        private set
    open var isHit: Boolean = true

    open fun calculatePoint(aceToBig: Boolean = false): Int = cards.sumBy { it.getPoint(aceToBig) }

    fun addCard(card: Card) {
        val currentCards = cards.toMutableList()
        currentCards.add(card)
        cards = currentCards.toList()
    }

    fun isBusted(): Boolean {
        return calculatePoint() > BLACKJACK_POINT
    }
}
