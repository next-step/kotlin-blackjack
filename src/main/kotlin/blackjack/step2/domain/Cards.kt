package blackjack.step2.domain

class Cards(private val cards: MutableList<Card>) {
    val all: List<Card>
        get() = cards.toList()

    fun add(card: Card): Cards {
        cards.add(card)
        return this
    }

    fun calculateScore(): Int {
        val possibleScores = calculateAllPossibleScores()
        return selectBestScore(possibleScores)
    }

    private fun calculateAllPossibleScores(): List<Int> {
        return cards.fold(initialScores()) { scores, card ->
            combineScores(scores, card)
        }
    }

    private fun initialScores(): List<Int> = listOf(0)

    private fun combineScores(
        currentScores: List<Int>,
        card: Card,
    ): List<Int> {
        return currentScores.flatMap { currentScore ->
            card.number.scores.map { score -> currentScore + score }
        }
    }

    private fun selectBestScore(scores: List<Int>): Int {
        return scores.filter { it <= 21 }.maxOrNull() ?: scores.min()
    }

    companion object {
        fun of(cards: List<Card>): Cards {
            return Cards(cards.toMutableList())
        }
    }
}
