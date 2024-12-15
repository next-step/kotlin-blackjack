package blackjack.flow

import blackjack.card.Deck
import blackjack.participant.Dealer
import blackjack.participant.Player
import blackjack.policy.MoreCardPolicy
import blackjack.view.OutputView
import blackjack.view.policy.InputMoreCardPolicy

data class TurnMachine(
    val deck: Deck,
    val moreCardPolicy: MoreCardPolicy = InputMoreCardPolicy,
    val outputView: OutputView = OutputView,
) {
    fun playerTurn(player: Player) {
        while (!player.isBust()) {
            val isMoreCard = moreCardPolicy.isMoreCard(player.getName())
            if (!isMoreCard) {
                player.stand()
                break
            }
            player.take(deck.pick())
            player.refreshState()
            OutputView.printPlayerCard(player)
        }
    }

    fun dealerTurn(dealer: Dealer) {
        if (dealer.isMoreCard()) {
            dealer.take(listOf(deck.pick()))
            outputView.printDealerMoreCard()
        }
        dealer.stand()
    }
}
