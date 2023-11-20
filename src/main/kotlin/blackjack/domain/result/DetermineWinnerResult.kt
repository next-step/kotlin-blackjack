package blackjack.domain.result

import blackjack.domain.player.Players

data class DetermineWinnerResult(
    val players: Players,
) : Result()
