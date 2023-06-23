package blackjack.domain

fun interface GameOutcomeCalculator {
    fun calculate(players: Players): GameResult
}
