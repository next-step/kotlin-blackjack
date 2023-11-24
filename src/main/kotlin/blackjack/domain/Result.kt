package blackjack.domain

enum class Result(val inKorean: String) {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    companion object {
        fun of(hasWon: Boolean?): Result {
            return when (hasWon) {
                true -> WIN
                false -> LOSE
                null -> DRAW
            }
        }
    }
}
