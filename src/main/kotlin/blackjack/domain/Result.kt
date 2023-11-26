package blackjack.domain

enum class Result(val inKorean: String) {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    fun resultOnDealer(): Result {
        return when (this) {
            WIN -> LOSE
            DRAW -> DRAW
            LOSE -> WIN
        }
    }

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
