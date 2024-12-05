package blackjack.core

import blackjack.presentation.ResultView

class Turn(private val player: Player, private val cardDispenser: CardDispenser, private val finishTurnCondition: () -> Boolean) {
    fun process() {
        while (player.status == Status.HIT) {
            dealCardToPlayer()
        }
    }

    private fun dealCardToPlayer() {
        if (finishTurnCondition()) {
            cardDispenser.deal(player)
            ResultView.printPlayerCards(player)
            player.checkBust()
        } else {
            player.status = Status.STAND
        }
    }
}
