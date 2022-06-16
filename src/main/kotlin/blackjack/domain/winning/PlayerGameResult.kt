package blackjack.domain.winning

import blackjack.domain.player.Player

data class PlayerGameResult(
    val player: Player,
    val winCount: Int,
    val looseCount: Int,
    val tieCount: Int
)
