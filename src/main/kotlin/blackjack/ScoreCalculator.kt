package blackjack

object ScoreCalculator {

    fun calc(cards: List<Card>): Int {
        return cards.map { it.score }.reduce { acc, i -> acc + i }
    }
}