package blackjack.domain

object GameRules {
    fun determineResult(
        player: Participant,
        dealer: Participant,
    ): GameResult {
        return when {
            player.isBlackjack() && dealer.isBlackjack() -> GameResult.DRAW
            player.isBlackjack() -> GameResult.WIN
            dealer.isBlackjack() -> GameResult.LOSE
            player.score > 21 -> GameResult.LOSE
            dealer.score > 21 -> GameResult.WIN
            player.score > dealer.score -> GameResult.WIN
            player.score == dealer.score -> GameResult.DRAW
            else -> GameResult.LOSE
        }
    }
}
