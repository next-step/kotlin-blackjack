package domain

data class GameResult(val result: Map<String, Long>) {
    companion object {
        fun of(
            dealer: Dealer,
            players: Players,
        ): GameResult {
            var dealerWinAmount = 0L
            val results: MutableMap<String, Long> = mutableMapOf()

            players.players.forEach { player ->
                PlayerResultType.calculate(player, dealer).apply {
                    results[player.name] = this
                    dealerWinAmount -= this
                }
            }

            results.put(dealer.name, dealerWinAmount)
            return GameResult(results)
        }
    }
}

enum class PlayerResultType(private val ratio: Double) {
    BLACKJACK(1.5),
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0),
    ;

    companion object {
        fun calculate(
            player: Player,
            dealer: Dealer,
        ): Long {
            return when {
                player.isBlackjack() -> (BLACKJACK.ratio * player.bettingAmount).toLong()
                player.isBust() -> (LOSE.ratio * player.bettingAmount).toLong()
                dealer.isBust() -> (WIN.ratio * player.bettingAmount).toLong()
                player.getScore() < dealer.getScore() -> (LOSE.ratio * player.bettingAmount).toLong()
                player.getScore() > dealer.getScore() -> (WIN.ratio * player.bettingAmount).toLong()
                else -> (DRAW.ratio * player.bettingAmount).toLong()
            }
        }
    }
}
