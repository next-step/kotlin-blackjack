package blackjack.domain

class GameResult(
    players: List<Player>,
    dealer: Dealer,
) {
    val playerResults: List<PlayerResult> = players.map { getPlayerResult(it, dealer) }

    private val countPerPlayerResult: Map<PlayerResult, Int> = playerResults.groupingBy { it }
        .eachCount()

    fun getDealerResult(): Map<PlayerResult, Int> {
        return mapOf(
            PlayerResult.WIN to (countPerPlayerResult[PlayerResult.LOSE] ?: 0),
            PlayerResult.DRAW to (countPerPlayerResult[PlayerResult.DRAW] ?: 0),
            PlayerResult.LOSE to (countPerPlayerResult[PlayerResult.WIN] ?: 0),
        )
    }

    private fun getPlayerResult(player: Player, dealer: Dealer): PlayerResult {
        if (player.isBust()) {
            return PlayerResult.LOSE
        }
        if (dealer.isBust()) {
            return PlayerResult.WIN
        }

        return PlayerResult.of(player.score, dealer.score)
    }
}
