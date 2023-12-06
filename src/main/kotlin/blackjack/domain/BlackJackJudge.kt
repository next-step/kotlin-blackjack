package blackjack.domain

import blackjack.domain.player.CardPlayer
import blackjack.domain.player.Player
import blackjack.domain.result.game.VictoryStatus

object BlackJackJudge {
    private const val BUST_THRESHOLD = 21
    private const val BUST_SCORE = 0

    fun judgeVictory(player: Player, dealer: Dealer): VictoryStatus =
        when {
            player.isBust() -> VictoryStatus.LOSS
            player.gameScore() > dealer.gameScore() -> VictoryStatus.WIN
            player.gameScore() == dealer.gameScore() -> VictoryStatus.PUSH
            else -> VictoryStatus.LOSS
        }

    private fun CardPlayer.gameScore(): Int =
        if (this.isBust()) BUST_SCORE
        else this.score.cardScore

    private fun CardPlayer.isBust() = score.cardScore > BUST_THRESHOLD
}
