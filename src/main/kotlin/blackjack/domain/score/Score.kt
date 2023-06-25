package blackjack.domain.score

data class Score(
    val value: Int
) {

    val isAlive = value <= WIN_NUMBER
    val isBust = isAlive.not()

    companion object {

        private const val WIN_NUMBER = 21
    }
}
