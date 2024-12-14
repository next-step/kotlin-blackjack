package blackjack.domain

class GameUser(val name: String) {
    private var doneGame = false
    val cards = mutableListOf<BlackJackCard>()
    val points: Int
        get() = calculatePoints()

    fun doneGame(status: Boolean) {
        doneGame = status
    }

    fun isDoneGame(): Boolean {
        return doneGame || points >= BLACKJACK_POINT
    }

    fun addCard(card: BlackJackCard) {
        cards.add(card)
        println(points)
    }

    private fun calculatePoints(): Int {
        val sumPoint =
            cards.sumOf {
                it.getPoint()
            }
        val aceCount = cards.any{ it.isAceCard() }
        return consumeAceCard(sumPoint, aceCount)
    }

    private fun consumeAceCard(
        sumPoint: Int,
        hasAceCard: Boolean,
    ): Int {
        return sumPoint +
            if (hasAceCard && sumPoint <= ACE_CARD_THRESHOLD) {
                ACE_CARD_EXTRA_POINT
            } else {
                0
            }
    }

    companion object {
        private const val BLACKJACK_POINT = 21
        private const val ACE_CARD_THRESHOLD = 11
        private const val ACE_CARD_EXTRA_POINT = 10
    }
}
