package blackjack.domain

class BlackjackGameResult(
    private val dealer: Dealer,
    players: List<Player>,
) {
    private val result: MutableMap<Player, GameMatchResult> = mutableMapOf()

    init {
        players.forEach { player ->
            result[player] = player.compareWithDealer(dealer)
        }
    }

    fun extractPlayerGameResult(): Map<Player, GameMatchResult> {
        return result.toMap()
    }
}
