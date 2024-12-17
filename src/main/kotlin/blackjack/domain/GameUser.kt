package blackjack.domain

interface GameUserInterface {
    val name: String
    val cards: MutableList<BlackJackCard>
    val points: Int

    fun setDoneGame(status: Boolean)

    fun isDoneGame(): Boolean

    fun comparePoints(opponent: GameUserInterface): Boolean
}

class GameUser(override val name: String) : GameUserInterface {
    private var doneGame = false
    override val cards = mutableListOf<BlackJackCard>()
    override val points: Int
        get() = calculatePoints()

    override fun setDoneGame(status: Boolean) {
        doneGame = status
    }

    override fun isDoneGame(): Boolean {
        return doneGame || calculatePoints() >= BLACKJACK_POINT
    }

    override fun comparePoints(opponent: GameUserInterface): Boolean {
        return (points <= BLACKJACK_POINT) &&
            ((opponent.points > BLACKJACK_POINT) || (points > opponent.points))
    }

    private fun calculatePoints(): Int {
        val sumPoint =
            cards.sumOf {
                it.getPoint()
            }
        val aceCount = cards.any { it.isAceCard() }
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
