package blackjack.domain.game

import blackjack.domain.player.PlayerName

data class PlayerMatchResult(
    val playerName: PlayerName,
    val matchResultType: MatchResultType,
)
