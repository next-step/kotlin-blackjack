package blackjack.domain

enum class ResultType(val symbol: String, val predicate: (Int) -> Boolean) {
    WIN("승", { compareResult -> compareResult > 0 }),
    DRAW("무", { compareResult -> compareResult == 0 }),
    LOSE("패", { compareResult -> compareResult < 0 });

    companion object {
        fun of(compareResult: Int): ResultType {
            return values().first { it.predicate(compareResult) }
        }
    }
}
