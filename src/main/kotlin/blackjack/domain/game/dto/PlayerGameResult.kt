package blackjack.domain.game.dto

import blackjack.domain.game.MatchResult
import blackjack.domain.player.Player

data class PlayerGameResult(
    val player: Player,
    val result: MatchResult,
)
