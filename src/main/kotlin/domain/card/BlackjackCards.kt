package domain.card
class BlackjackCards(private val cards: List<BlackjackCard>) {

    fun isDrawable(): Boolean {
        val sum = cards.sumOf { it.number.score }
        return sum < CARD_NUMBER_SUM_MAX
    }

    companion object {
        const val CARD_NUMBER_SUM_MAX = 21
    }
}
