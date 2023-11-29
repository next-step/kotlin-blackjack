package blackjack.domain

import blackjack.domain.player.Player

data class BlackjackResult(
    val playerResult: List<PlayerResult>
) {
    val dealerWin get() = playerResult.count { it.isWin.not() }
    val dealerLose get() = playerResult.count { it.isWin }
}

data class PlayerResult(
    val player: Player,
    val isWin: Boolean,
)
