package blackjack.domain

class Score(
    val value: Int
) {
    init {
        require(value > 0) { "점수값은 0보다 커야합니다." }
    }
}
