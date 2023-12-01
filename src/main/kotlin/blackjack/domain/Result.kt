package blackjack.domain

enum class Result {
    WIN, DRAW, LOSE;

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
