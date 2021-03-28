package blackjack.domain

enum class ResultType(val symbol: String, val predicate: (Int) -> Boolean, val profitRate: Double) {
    WIN("승", { compareResult -> compareResult > 0 }, 1.0),
    DRAW("무", { compareResult -> compareResult == 0 }, 0.0),
    LOSE("패", { compareResult -> compareResult < 0 }, -1.0),
    BLACKJACK_WIN("승", { compareResult -> compareResult > 0 }, 1.5);

    companion object {
        fun of(score1: Score, score2: Score): ResultType {
            val resultType = values().first { it.predicate(score1.score.compareTo(score2.score)) }
            if (resultType == WIN && score1.isBlackjack) {
                return BLACKJACK_WIN
            }
            return resultType
        }
    }
}
