package blackjack.domain.dto

import blackjack.domain.MatchResult
import blackjack.domain.Player

data class PlayerGameResult(
    val player: Player,
    val result: MatchResult,
)
