package blackjack.domain

interface Player {
    val name: String
    val cards: Set<BlackjackCard>
    val score: Int
        get() = calculateScore()

    infix fun receive(card: BlackjackCard)

    private fun calculateScore(): Int {
        val scores = cards.map { it.number.score }.fold(listOf(0)) { accumulator, scores ->
            accumulator.flatMap { sum -> scores.map { sum + it } }
        }
        return scores.filter { it <= 21 }.maxOrNull() ?: scores.max()
    }
}
