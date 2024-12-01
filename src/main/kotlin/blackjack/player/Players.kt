package blackjack.player

import blackjack.round.RoundResult

class Players(
    val players: List<Player>,
) {
    companion object {
        fun generateFromNames(playerNames: List<String>) = Players(players = playerNames.map { Player.fromName(name = it) })
    }

    fun updateCardStatus(roundResults: List<RoundResult>): Players = Players(players = roundResults.map { it.player })
}
