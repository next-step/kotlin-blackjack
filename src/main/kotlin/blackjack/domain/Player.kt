package blackjack.domain

open class Player(val name: String) {
    var cards: List<Card> = listOf()
        private set

    open fun drawable(): Boolean {
        return getPoints() < BLACKJACK_POINT
    }

    open fun addCard(card: Card) {
        cards = cards + card
    }

    fun addCards(cards: List<Card>) {
        this.cards = this.cards + cards
    }

    fun getPoints(): Int {
        val points = cards.sumOf { it.denomination.point }

        if (Denomination.ACE in cards.map { it.denomination } && points <= SOFT_HAND_CRITERIA) return points + SOFT_HAND_POINT

        return points
    }

    companion object {
        private const val BLACKJACK_POINT = 21
        private const val SOFT_HAND_CRITERIA = 11
        private const val SOFT_HAND_POINT = 10
    }
}
