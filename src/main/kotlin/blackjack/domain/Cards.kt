package blackjack.domain

class Cards private constructor(
    val cards: List<Card>,
) {
    fun copy(): Cards = Cards(cards.toList())

    fun add(newCard: Card): Cards {
        val newCards = cards.toMutableList()
        newCards.add(newCard)
        return Cards(newCards)
    }

    fun calculateOptimalSum(): Int {
        val sum = calculateSum()
        return adjustAceSum(sum)
    }

    private fun calculateSum(): Int = cards.sumOf { it.numbers.max() }

    private fun adjustAceSum(sum: Int): Int {
        var adjustedSum = sum
        var aceCount = cards.count { it.rank == Ranks.ACE }

        while (0 < aceCount && adjustedSum > BLACK_JACK_SCORE) {
            adjustedSum -= 10
            aceCount--
        }

        return adjustedSum
    }

    companion object {
        const val BLACK_JACK_SCORE: Int = 21
        fun empty(): Cards = Cards(emptyList())
    }
}
