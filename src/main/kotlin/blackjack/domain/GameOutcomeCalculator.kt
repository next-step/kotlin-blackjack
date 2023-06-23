package blackjack.domain

fun interface GameOutcomeCalculator {
    fun calculate(dealer: Dealer, players: Players): GameResult
}
