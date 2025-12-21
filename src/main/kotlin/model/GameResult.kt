package model

class GameResult(
    private val playerWins: Map<String, Long>,
) {
    val dealerWin = playerWins.values.sum() * -1

    fun getPlayerWin(player: Player): Long {
        return playerWins[player.name] ?: 0L
    }

    companion object {
        fun of(
            dealer: Dealer,
            players: Players,
        ): GameResult {
            val playerWins: MutableMap<String, Long> = mutableMapOf()
            players.forEach {
                val win: Long = when {
                    it.isBust() -> -it.betAmount
                    dealer.isBlackJack() && it.isBlackJack() -> 0
                    it.isBlackJack() -> (it.betAmount * 3) / 2
                    dealer.isBust() -> it.betAmount
                    it.calculateScore() > dealer.calculateScore() -> it.betAmount
                    it.calculateScore() < dealer.calculateScore() -> -it.betAmount
                    else -> 0
                }
                playerWins[it.name] = win
            }
            return GameResult(playerWins)
        }
    }
}
