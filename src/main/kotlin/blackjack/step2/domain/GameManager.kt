package blackjack.step2.domain

import blackjack.step2.view.InputView

class GameManager(private val cardPicker: CardPicker) {
    fun playTurn(participant: Participant): Participant {
        return generateSequence(participant) { current ->
            when (current) {
                is Player -> processPlayerTurn(current)
                is Dealer -> processDealerTurn(current)
            }
        }.last()
    }

    private fun processPlayerTurn(player: Player): Player? {
        return if (player.isBust()) {
            null
        } else {
            InputView.askForMoreCard(player).takeIf { it }?.let {
                val card = cardPicker.pick()
                player.pickCard(card).also { InputView.printPlayerCards(it) }
            }
        }
    }

    private fun processDealerTurn(dealer: Dealer): Dealer? {
        return if (!dealer.canDraw()) {
            null
        } else {
            InputView.notifyDealerDraw()
            val card = cardPicker.pick()
            dealer.pickCard(card)
        }
    }
}
