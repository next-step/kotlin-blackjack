package domain.card

class Cards(private val Cards: List<Card>) : List<Card> by Cards {

    val sum: Int

    init {
        sum = this.sum()
    }

    fun isDrawable(): Boolean = this.sum <= CARD_NUMBER_SUM_MAX

    private fun sum(): Int {
        val scoreSum = Cards.sumOf { it.number.score }
        return Cards.filter { it.number.isAce() }
            .fold(scoreSum) { currentSum, _ ->
                if (currentSum + ACE_SPECIAL_SCORE <= CARD_NUMBER_SUM_MAX) {
                    currentSum + ACE_SPECIAL_SCORE
                } else {
                    currentSum
                }
            }
    }

    companion object {
        private const val CARD_NUMBER_SUM_MAX = 21
        private const val ACE_SPECIAL_SCORE = 10
        fun isBlackjack(card1: Card, card2: Card): Boolean =
            (card1.number.isAce() && card2.number.isTenScore()) ||
                (card2.number.isAce() && card1.number.isTenScore())
    }
}
