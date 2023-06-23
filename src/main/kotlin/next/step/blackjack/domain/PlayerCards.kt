package next.step.blackjack.domain

data class PlayerCards(val cards: MutableList<Card>) {
    fun add(card: Card) {
        cards.add(card)
    }

    fun isBurst(): Boolean {
        return sumCardsPoint() > BLACKJACK_POINT
    }

    private fun sumCardsPoint() = cards.sumOf { it.face.point }

    fun isBlackJack(): Boolean = sumCardsPoint() == BLACKJACK_POINT

    companion object {
        private const val BLACKJACK_POINT = 21
        fun of(cards: List<Card>): PlayerCards = PlayerCards(cards.toMutableList())

    }
}