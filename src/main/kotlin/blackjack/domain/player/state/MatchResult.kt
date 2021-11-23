package blackjack.domain.player.state

enum class MatchResult(private val compareToValue: Int) {
    WIN(1), DRAW(0), LOSE(-1);

    companion object {
        fun values(compareToValue: Int): MatchResult = values()
            .find { it.compareToValue == compareToValue }
            ?: throw ClassNotFoundException()
    }
}
