package blackjack.core.turn

import blackjack.core.player.Player
import blackjack.presentation.InputView

object PlayerTurnCondition : TurnCondition {
    override fun canGo(player: Player): Boolean {
        return InputView.getCard(player)
    }
}
