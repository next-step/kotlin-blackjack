package blackjack.domain

import blackjack.view.input.InputView
import blackjack.view.result.ResultView

class Dealer(private val deck: DealerCardDeck) {
    fun deliverBasicCards(players: Players) {
        players.players
            .forEach { player -> repeat(BASIC_CARD_NUMBER) { player.receiveCard(deck.getNextCard()) } }
    }

    fun deliverAdditionalCards(players: Players, inputView: InputView, resultView: ResultView) {
        players.players
            .forEach { player -> deliverAdditionalCards(inputView, resultView, player) }
    }

    private fun deliverAdditionalCards(inputView: InputView, resultView: ResultView, player: Player) {
        while (player.canReceiveAdditionalCard() && inputView.askToReceiveAdditionalCardOrNot(player)) {
            deliverAdditionalCard(player)
            resultView.showPlayerCards(player, true)
        }
    }

    private fun deliverAdditionalCard(player: Player) {
        player.receiveCard(deck.getNextCard())
    }

    companion object {
        const val BASIC_CARD_NUMBER = 2
    }
}
