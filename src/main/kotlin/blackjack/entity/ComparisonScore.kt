package blackjack.entity

sealed class ComparisonScore {
    data class Single(val score: Int) : ComparisonScore()

    data class Multiple(val scores: List<Int>) : ComparisonScore()
}
