package blackjack.core.turn

import blackjack.core.player.Player
import blackjack.presentation.ResultView

object DealerTurnCondition : TurnCondition {
    private const val DEALER_SCORE = 16

    override fun canGo(player: Player): Boolean {
        return if (player.cards.point() <= DEALER_SCORE) {
            ResultView.printDealerDraw()
            true
        } else {
            false
        }
    }
}
