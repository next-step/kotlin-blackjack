package blackjack.model.player

class PlayerGameResults private constructor(
    val results: List<PlayerGameResult>
) {

    val players
        get() = results.map { it.player }

    companion object {
        private const val LOST_DECISION_BOUNDARY_SCORE_OF_DEALER = 21

        fun from(players: Players): PlayerGameResults {
            val dealer = players.players
                .firstOrNull { it.isDealer }

            require(dealer != null) { "딜러가 존재하지 않습니다." }

            val otherPlayers = players.players
                .filter { it != dealer }

            if (dealer.isScoreGreaterThan(LOST_DECISION_BOUNDARY_SCORE_OF_DEALER)) {
                return otherPlayers.map { PlayerGameResult(it, 1, 0) }
                    .plus(PlayerGameResult(dealer, 0, 1))
                    .let(::PlayerGameResults)
            }

            return otherPlayers.map { PlayerGameResult.ofPlayer(dealer, it) }
                .plus(PlayerGameResult.ofDealer(dealer, otherPlayers))
                .let(::PlayerGameResults)
        }
    }
}
