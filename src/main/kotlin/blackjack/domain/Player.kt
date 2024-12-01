package blackjack.domain

class Player(
    val name: String,
    private val _cardSet: MutableSet<BlackjackCard> = mutableSetOf(),
) {

    val cardSet: Set<BlackjackCard>
        get() = _cardSet

    val score: Int
        get() = calculateScore()

    infix fun receive(card: BlackjackCard) {
        _cardSet.add(card)
    }

    private fun calculateScore(): Int {
        val scores = cardSet.map { it.number.score }.fold(listOf(0)) { accumulator, scores ->
            accumulator.flatMap { sum -> scores.map { sum + it } }
        }
        return scores.filter { it <= 21 }.maxOrNull() ?: scores.max()
    }
}
