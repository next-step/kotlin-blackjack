package blackjack.domain.result

import blackjack.domain.player.GamePlayer
import blackjack.domain.result.match.MatchState

data class GameResult(
    val player: GamePlayer,
    val matchState: MatchState,
)
