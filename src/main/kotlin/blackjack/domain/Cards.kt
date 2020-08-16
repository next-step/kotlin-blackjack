package blackjack.domain

data class Cards(
    val values: List<Card>
) {
    val totalScore: Int = Score.from(values)

    operator fun plus(card: Card): Cards = Cards(this.values + card)

    operator fun plus(cards: List<Card>): Cards = Cards(this.values + cards)

    operator fun plus(cards: Cards): Cards = Cards(this.values + cards.values)

    fun isEmpty() = values.isEmpty()

    fun canPlay() = isNotBlackJack() && isNotBusted()

    private fun isNotBlackJack() = !isBlackJack()

    private fun isBlackJack(): Boolean {
        return totalScore == Score.BLACK_JACK && values.size == BLACK_JACK_CARD_COUNT
    }

    fun isNotBusted() = !isBusted()

    private fun isBusted(): Boolean {
        return totalScore > Score.BLACK_JACK
    }

    override fun toString(): String {
        return values.joinToString(", ")
    }

    companion object {
        private const val BLACK_JACK_CARD_COUNT = 2

        fun empty() = Cards(emptyList())

        fun denominationsOf(vararg values: String) = Cards(values.map { Card.denominationOf(it) })
    }
}
