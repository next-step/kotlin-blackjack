package blackjack.domain

class Cards(
    private val value: List<Card> = listOf(Card.of(), Card.of())
) {

    override fun toString(): String {
        return value.toString()
    }

    operator fun plus(card: Card): Cards {
        return Cards(value + card)
    }

    fun calculateScore(): Int {
        val aceCount = value.count { it.denomination == Denomination.ACE }

        val sumOfScore = value.sumOf { it.score }

        if (aceCount == 1 && sumOfScore <= ACE_MAX_SCORE) {
            return sumOfScore + PLUS_ACE_MAX_SCORE
        }

        if (aceCount > 1 && sumOfScore + PLUS_ACE_MAX_SCORE <= 21) {
            return sumOfScore + PLUS_ACE_MAX_SCORE
        }

        return sumOfScore
    }

    companion object {
        private const val PLUS_ACE_MAX_SCORE = 10
        private const val ACE_MAX_SCORE = 11
    }
}
