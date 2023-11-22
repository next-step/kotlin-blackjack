package blackjack.domain.result

import blackjack.domain.player.Players

data class DealInitialCardResult(
    val players: Players
) : Result()
