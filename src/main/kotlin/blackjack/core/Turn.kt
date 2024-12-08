package blackjack.core

import blackjack.presentation.ResultView

class Turn(private val player: Player, private val cardDispenser: CardDispenser, private val finishTurnCondition: () -> Boolean) {
    fun process() {
        while (player.cards.status == Status.HIT) {
            dealCardToPlayer()
        }
    }

    private fun dealCardToPlayer() {
        if (finishTurnCondition()) {
            cardDispenser.deal(player)
            ResultView.printPlayerCards(player)
            player.cards.checkBust()
        } else {
            player.cards.status = Status.STAND
        }
    }
}
