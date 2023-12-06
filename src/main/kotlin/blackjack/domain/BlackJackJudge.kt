package blackjack.domain

import blackjack.domain.player.CardPlayer
import blackjack.domain.player.Player
import blackjack.domain.result.game.VictoryStatus

object BlackJackJudge {
    private const val BUST_SCORE = 0
    private const val BLACK_JACK_CARD_COUNT = 2
    private const val BLACK_JACK_SCORE = 21

    fun judgeVictory(player: Player, dealer: Dealer): VictoryStatus =
        when {
            player.isBust() -> VictoryStatus.LOSS
            player.gameScore() > dealer.gameScore() -> VictoryStatus.WIN
            player.gameScore() == dealer.gameScore() -> VictoryStatus.PUSH
            else -> VictoryStatus.LOSS
        }

    fun isBlackJack(player: Player): Boolean =
        (player isSameCardCount BLACK_JACK_CARD_COUNT) && (player isSameScore BLACK_JACK_SCORE)

    private fun CardPlayer.gameScore(): Int =
        if (this.isBust()) BUST_SCORE
        else this.score.value

    private fun CardPlayer.isBust() = score.value > BLACK_JACK_SCORE
}
