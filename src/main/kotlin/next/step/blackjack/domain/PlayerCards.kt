package next.step.blackjack.domain

data class PlayerCards(val cards: MutableList<Card>) {
    fun add(card: Card) {
        cards.add(card)
    }

    fun isBlackJack(): Boolean = minSumCardsPoint() == BLACKJACK_POINT || maxSumCardsPoint() == BLACKJACK_POINT

    private fun minSumCardsPoint(): Int = cards.sumOf { it.minPoint() }

    private fun maxSumCardsPoint(): Int = cards.sumOf { it.maxPoint() }

    fun isBurst(): Boolean {
        return minSumCardsPoint() > BLACKJACK_POINT
    }

    fun point(): Int = when {
        isBlackJack() -> BLACKJACK_POINT
        maxSumCardsPoint() < BLACKJACK_POINT -> maxSumCardsPoint()
        else -> minSumCardsPoint()
    }

    fun size() = cards.size

    fun descs(): Set<String> = cards.map { it.desc() }.toSet()

    companion object {
        private const val BLACKJACK_POINT = 21
        fun of(cards: List<Card>): PlayerCards = PlayerCards(cards.toMutableList())
    }
}
