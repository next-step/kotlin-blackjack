package blackjack.domain

class Player(
    val name: String,
    private val _cards: MutableSet<BlackjackCard> = mutableSetOf(),
) {

    val cards: Set<BlackjackCard>
        get() = _cards

    val score: Int
        get() = calculateScore()

    infix fun receive(card: BlackjackCard) {
        _cards.add(card)
    }

    private fun calculateScore(): Int {
        val scores = cards.map { it.number.score }.fold(listOf(0)) { accumulator, scores ->
            accumulator.flatMap { sum -> scores.map { sum + it } }
        }
        return scores.filter { it <= 21 }.maxOrNull() ?: scores.max()
    }
}
