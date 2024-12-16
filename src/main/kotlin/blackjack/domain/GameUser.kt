package blackjack.domain

interface GameUserInterface {
    fun getName(): String

    fun doneGame(status: Boolean)

    fun isDoneGame(): Boolean

    fun addCard(card: BlackJackCard)

    fun getCards(): List<BlackJackCard>

    fun getPoints(): Int

    fun comparePoints(opponent: GameUserInterface): Boolean
}

class GameUser(private val name: String) : GameUserInterface {
    private var doneGame = false
    private val cards = mutableListOf<BlackJackCard>()

    override fun getName(): String {
        return name
    }

    override fun doneGame(status: Boolean) {
        doneGame = status
    }

    override fun isDoneGame(): Boolean {
        return doneGame || calculatePoints() >= BLACKJACK_POINT
    }

    override fun addCard(card: BlackJackCard) {
        cards.add(card)
    }

    override fun getCards(): List<BlackJackCard> {
        return cards
    }

    override fun getPoints(): Int {
        return calculatePoints()
    }

    override fun comparePoints(opponent: GameUserInterface): Boolean {
        return (getPoints() <= BLACKJACK_POINT) &&
            ((opponent.getPoints() > BLACKJACK_POINT) || (getPoints() > opponent.getPoints()))
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
