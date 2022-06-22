package blackjack.model.player

class PlayerGameResults private constructor(
    val results: List<PlayerGameResult>
) {

    val players
        get() = results.map { it.player }

    companion object {
        fun from(players: Players): PlayerGameResults {
            val dealer = players.players
                .firstOrNull { it.isDealer }

            require(dealer != null) { "딜러가 존재하지 않습니다." }

            val otherPlayers = players.players
                .filter { it != dealer }

            val dealerGameResult = PlayerGameResult.ofDealer(dealer, otherPlayers)
            val otherPlayerGameResults = otherPlayers.map { PlayerGameResult.ofPlayer(dealer, it) }

            return otherPlayerGameResults.plus(dealerGameResult)
                .let(::PlayerGameResults)
        }
    }
}
