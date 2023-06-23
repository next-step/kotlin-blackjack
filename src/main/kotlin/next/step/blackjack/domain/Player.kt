package next.step.blackjack.domain

data class Player(val name: String, val cards: MutableList<Card>) {

    fun hit(card: Card) {
        cards.add(card)
    }

    fun isBurst(): Boolean {
        return sumCardsPoint() > BLACKJACK_POINT
    }

    private fun sumCardsPoint() = cards.sumOf { it.face.point }

    fun isBlackJack(): Boolean = sumCardsPoint() == BLACKJACK_POINT

    companion object {
        private const val BLACKJACK_POINT = 21

        fun of(name: String, cards: List<Card>): Player = Player(name, cards.toMutableList())
    }
}