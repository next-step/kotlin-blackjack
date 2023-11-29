package blackjack.domain.result.game

import blackjack.domain.player.DealerPlayer
import blackjack.domain.player.Player

data class PlayerResult(
    val player: Player,
    val status: VictoryStatus,
) {
    companion object {
        fun of(player: Player, dealerPlayer: DealerPlayer): PlayerResult {
            val playerScore = player.score
            val dealerScore = dealerPlayer.score
            val status = when {
                playerScore.isBust -> VictoryStatus.LOSS
                playerScore isGreaterGameScoreThan dealerScore -> VictoryStatus.WIN
                playerScore isSameGameScoreTo dealerScore -> VictoryStatus.PUSH
                else -> VictoryStatus.LOSS
            }
            return PlayerResult(player, status)
        }
    }
}
