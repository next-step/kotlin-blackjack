package blackjack.domain

class BlackjackGameResult(
    private val dealer: Dealer,
    players: List<Player>,
) {
    private val result: Map<Player, GameMatchResult> =
        players.associateWith { player ->
            player.compareWithDealer(dealer)
        }

    fun extractPlayerGameResult(): Map<Player, GameMatchResult> {
        return result
    }
}
