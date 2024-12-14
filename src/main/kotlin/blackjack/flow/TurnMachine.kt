package blackjack.flow

import blackjack.card.Deck
import blackjack.participant.Dealer
import blackjack.participant.Player
import blackjack.policy.InputMoreCardPolicy
import blackjack.policy.MoreCardPolicy
import blackjack.view.OutputView

data class TurnMachine(
    val deck: Deck,
    val moreCardPolicy: MoreCardPolicy = InputMoreCardPolicy,
    val outputView: OutputView = OutputView,
) {
    fun playerTurn(player: Player) {
        while (!player.isBust()) {
            val isMoreCard = moreCardPolicy.isMoreCard(player.getName())
            if (!isMoreCard) {
                break
            }
            player.take(listOf(deck.pick()))
            OutputView.printPlayerCard(player)
        }
    }

    fun dealerTurn(dealer: Dealer) {
        if (dealer.isMoreCard()) {
            dealer.take(listOf(deck.pick()))
            outputView.printDealerMoreCard()
        }
    }
}
