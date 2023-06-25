package domain.card

class Cards(private val cards: List<Card>) : List<Card> by cards {

    val sum: Int

    init {
        sum = this.sum()
    }

    fun isDrawable(): Boolean = this.sum <= CARD_NUMBER_SUM_MAX

    private fun sum(): Int {
        val scoreSum = cards.sumOf { it.number.score }
        return cards.filter { it.number.isAce() }
            .fold(scoreSum) { currentSum, _ ->
                if (currentSum + ACE_SPECIAL_SCORE <= CARD_NUMBER_SUM_MAX) {
                    currentSum + ACE_SPECIAL_SCORE
                } else {
                    currentSum
                }
            }
    }

    fun isBlackjack(): Boolean = cards.size == BLACKJACK_CARD_COUNT && this.sum == CARD_NUMBER_SUM_MAX

    companion object {
        private const val CARD_NUMBER_SUM_MAX = 21
        private const val ACE_SPECIAL_SCORE = 10
        private const val BLACKJACK_CARD_COUNT = 2
    }
}
