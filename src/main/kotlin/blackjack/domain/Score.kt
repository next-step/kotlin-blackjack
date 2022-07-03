package blackjack.domain

@JvmInline
value class Score(private val cards: List<Card>) {

    val value: Int
        get() {
            val point = cards.sumOf { it.denomination.point }
            return if (cards.hasAce() && point <= SOFT_HAND_CRITERIA) point + SOFT_HAND_POINT
            else point
        }

    private fun List<Card>.hasAce() = Denomination.ACE in this.map { it.denomination }

    fun isBust(): Boolean {
        return value > BLACKJACK_POINT
    }

    fun isBlackjack(): Boolean {
        return value == BLACKJACK_POINT
    }

    companion object {
        private const val BLACKJACK_POINT = 21
        private const val SOFT_HAND_CRITERIA = 11
        private const val SOFT_HAND_POINT = 10
    }
}
