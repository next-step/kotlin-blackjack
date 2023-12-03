package blackjack.domain

enum class GameResult(
    val message: String
) {
    WIN("승"),
    LOSE("패"),
    DRAW("무승부");

    fun opposite(): GameResult {
        return when (this) {
            WIN -> LOSE
            LOSE -> WIN
            DRAW -> DRAW
        }
    }
}
