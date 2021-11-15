package blackjack

object ScoreHelper {

    fun sum(cards: List<Card>): Int = cards.sumOf { it.denomination.defaultScore }
}
