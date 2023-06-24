package domain.card

class BlackjackCards(private val cards: List<BlackjackCard>) : List<BlackjackCard> by cards {

    var sum: Int
        private set

    init {
        sum = this.sum()
    }

    fun isDrawable(): Boolean = this.sum <= CARD_NUMBER_SUM_MAX

    private fun sum(): Int {
        val (aceCards, nonAceCards) = cards.partition { it.number.isAce() }
        val nonAceSum = nonAceCards.sumOf { it.number.score }

        val (mins, maxs) = (0..aceCards.size)
            .map { (aceCards.size - it) * ACE_SPECIAL_SCORE + (it * CardNumber.ACE.score) + nonAceSum }
            .partition { it <= CARD_NUMBER_SUM_MAX }

        return if (mins.isEmpty()) maxs.min() else mins.max()
    }

    companion object {
        private const val CARD_NUMBER_SUM_MAX = 21
        private const val ACE_SPECIAL_SCORE = 11
    }
}
