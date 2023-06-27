package blackjack.domain.game

import blackjack.domain.gamer.PlayerName

data class PlayerMatchResult(
    val playerName: PlayerName,
    val matchResultType: MatchResultType,
)
