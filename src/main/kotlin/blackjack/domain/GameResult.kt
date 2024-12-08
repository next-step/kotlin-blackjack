package blackjack.domain

data class GameResult(
    val players: List<Player>,
    val dealer: Dealer,
) {
    private val results: Map<Player, Result> =
        players.associateWith { player ->
            dealer vs player
        }

    val dealerWins: Int
        get() = results.values.count { it == Result.LOSE }

    val dealerLosses: Int
        get() = results.values.count { it == Result.WIN }

    fun getResult(player: Player): Result = results[player] ?: Result.DRAW
}
