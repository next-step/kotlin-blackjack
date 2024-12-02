package blackjack.entity

sealed class ComparisonScore {
    data class Dealer(val score: Int) : ComparisonScore()

    data class Players(val scores: List<Int>) : ComparisonScore()
}
