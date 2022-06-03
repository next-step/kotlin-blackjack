package blackjack

interface Score

@JvmInline
value class SingleScore(val value: Int) : Score {
    init {
        require(value >= 0) { "점수는 0보다 커야 합니다" }
    }
}

@JvmInline
value class SelectableScore(val scores: List<SingleScore>) : Score
