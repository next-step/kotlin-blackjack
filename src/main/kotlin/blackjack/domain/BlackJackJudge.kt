package blackjack.domain

import blackjack.domain.player.CardPlayer
import blackjack.domain.player.Player
import blackjack.domain.result.game.VictoryStatus

object BlackJackJudge {
    private const val BLACK_JACK_CARD_COUNT = 2
    private const val BLACK_JACK_SCORE = 21

    fun judgeVictory(player: Player, dealer: Dealer): VictoryStatus =
        when {
            player.isBust() -> VictoryStatus.LOSS
            dealer.isBust() || player.score > dealer.score -> VictoryStatus.WIN
            player.score == dealer.score -> VictoryStatus.PUSH
            else -> VictoryStatus.LOSS
        }

    fun isBlackJack(player: Player): Boolean =
        (player isSameCardCount BLACK_JACK_CARD_COUNT) && (player isSameScore BLACK_JACK_SCORE)

    private fun CardPlayer.isBust() = score.value > BLACK_JACK_SCORE
}
