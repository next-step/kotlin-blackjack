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

            val dealerGameResults = otherPlayers.map { PlayerGameResult.of(dealer, it) }
            val dealerWinCount = dealerGameResults.sumOf { it.winCount }
            val dealerLostCount = dealerGameResults.sumOf { it.lostCount }

            return listOf(PlayerGameResult(dealer, dealerWinCount, dealerLostCount))
                .plus(otherPlayers.map { PlayerGameResult.of(it, dealer) })
                .let(::PlayerGameResults)
        }
    }
}
