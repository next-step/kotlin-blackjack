package blackjack.domain.result

import blackjack.domain.player.Player

data class DealToPlayerResult(
    val player: Player
) : Result()

