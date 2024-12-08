package blackjack.core.turn

import blackjack.core.player.Player
import blackjack.presentation.InputView

object PlayerTurnCondition : TurnCondition {
    override fun carGo(player: Player): Boolean {
        return InputView.getCard(player)
    }
}
