package blackjack

typealias CompareScore = (Int, Int) -> Boolean
enum class MatchResult(
    val message: String,
    private val compare: CompareScore
) {
    WIN("승", { e1: Int, e2: Int -> e1 > e2 }),
    LOSS("패", { e1: Int, e2: Int -> e1 < e2 }),
    DRAW("무", { e1: Int, e2: Int -> e1 == e2 });

    companion object {
        fun of(e1: Int, e2: Int) = MatchResult.values().find { it.compare(e1, e2) }
            ?: throw RuntimeException()

        fun reverse(matchResult: MatchResult) =
            when (matchResult) {
                WIN -> LOSS
                LOSS -> WIN
                DRAW -> DRAW
            }
    }
}
