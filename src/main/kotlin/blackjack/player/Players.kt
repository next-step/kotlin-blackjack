package blackjack.player

import blackjack.round.RoundResult

class Players(
    val players: List<Player>,
) {
    companion object {
        fun generateFromNames(playerNames: List<String>) = Players(players = playerNames.map { Player.fromName(name = it) })

        fun generateFromRoundResults(roundResults: List<RoundResult>) = Players(players = roundResults.map { it.player })
    }
}
