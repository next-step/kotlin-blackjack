package blackjack.model.player

class PlayerGameResults private constructor(
    private val results: List<PlayerGameResult>
) {

    companion object {
        fun from(players: Players) = players.players.map { PlayerGameResult.of(it, players) }
    }
}
