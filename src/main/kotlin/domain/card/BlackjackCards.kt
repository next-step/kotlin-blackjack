package domain.card

@JvmInline
value class BlackjackCards(private val cards: List<BlackjackCard>) : List<BlackjackCard> by cards {

    val sum: Int
        get() = cards.sumOf { it.number.score }

    fun isDrawable(): Boolean {
        val sum = cards.sumOf { it.number.score }
        return sum <= CARD_NUMBER_SUM_MAX
    }

    companion object {
        const val CARD_NUMBER_SUM_MAX = 21
    }
}
