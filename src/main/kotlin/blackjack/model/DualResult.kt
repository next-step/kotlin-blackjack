package blackjack.model

enum class DualResult(private val compare: (Int, Int) -> Boolean) {
    WIN({ score, otherScore -> score > otherScore }),
    PUSH({ score, otherScore -> score == otherScore }),
    LOSE({ score, otherScore -> score < otherScore });

    companion object {
        fun of(score: Int, otherScore: Int): DualResult {
            return values().find { it.compare(score, otherScore) }
                ?: throw IllegalArgumentException("해당되는 듀얼 결과가 없습니다.")
        }
    }
}
