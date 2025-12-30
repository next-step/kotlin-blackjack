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
                    results.put(player.name, this)
                    dealerWinAmount -= this
                }
            }

            results.put(dealer.name, dealerWinAmount)
            return GameResult(results)
        }
    }
}

enum class PlayerResultType(val score: Double) {
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
                player.isBust() -> (LOSE.score * player.bettingAmount).toLong()
                dealer.isBust() -> (WIN.score * player.bettingAmount).toLong()
                player.isBlackjack() -> (BLACKJACK.score * player.bettingAmount).toLong()
                player.cards.calculateScore() < dealer.cards.calculateScore() -> (LOSE.score * player.bettingAmount).toLong()
                player.cards.calculateScore() > dealer.cards.calculateScore() -> (WIN.score * player.bettingAmount).toLong()
                else -> (DRAW.score * player.bettingAmount).toLong()
            }
        }
    }
}
