package blackjack.domain.rule

class Score(
    val win: Int = 0,
    val lose: Int = 0
) {
    operator fun plus(other: Score): Score {
        return Score(this.win + other.win, this.lose + other.lose)
    }
}
