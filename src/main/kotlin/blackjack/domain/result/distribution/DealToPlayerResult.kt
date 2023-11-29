package blackjack.domain.result.distribution

import blackjack.domain.player.Player
import blackjack.domain.result.Result

data class DealToPlayerResult(
    val player: Player,
    val isSystemStand: Boolean,
) : Result()
