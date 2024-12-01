package blackjack.domain

class Player(
    val name: String,
) {
    private val cards: MutableSet<BlackjackCard> = mutableSetOf()

    val cardsSize
        get() = cards.size
    val score: Int
        get() = calculateScore()

    fun cardsToString() = cards.joinToString()

    infix fun receive(card: BlackjackCard) {
        cards.add(card)
    }

    private fun calculateScore(): Int {
        val scores = cards.map { it.number.score }.fold(listOf(0)) { accumulator, scores ->
            accumulator.flatMap { sum -> scores.map { sum + it } }
        }
        return scores.filter { it <= 21 }.maxOrNull() ?: scores.max()
    }
}
