package blackjack.domain.result

import blackjack.domain.player.Players

data class GameResult(
    val players: Players,
) : Result()
