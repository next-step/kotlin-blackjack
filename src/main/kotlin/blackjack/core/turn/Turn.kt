package blackjack.core.turn

import blackjack.core.CardDispenser
import blackjack.core.player.Player
import blackjack.presentation.ResultView

class Turn(private val player: Player, private val cardDispenser: CardDispenser) {
    fun process(turnCondition: TurnCondition) {
        while (canGo(player, turnCondition)) {
            dealCardToPlayer()
        }
    }

    private fun dealCardToPlayer() {
        cardDispenser.deal(player)
        ResultView.printPlayerCards(player)
    }

    private fun canGo(
        player: Player,
        turnCondition: TurnCondition,
    ): Boolean {
        return cardDispenser.checkRemainCard() && player.checkBust().not() && turnCondition.canGo(player)
    }
}
