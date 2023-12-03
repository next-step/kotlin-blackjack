package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Participants
import blackjack.domain.Player
import blackjack.view.BlackjackInputView
import blackjack.view.BlackjackOutputView

object BlackjackController {
    fun handle() {
        val namesInput = BlackjackInputView.readPlayerNamesInput()
        val dealer = Dealer()
        val players = namesInput.map { Player(it) }
        val participants = Participants(dealer, players)

        participants.drawInitialCards()
        BlackjackOutputView.printInitialCards(participants)

        players.forEach { it.action() }

        if (dealer.shouldReceiveCard()) {
            dealer.receiveCard(Deck.draw())
            BlackjackOutputView.printDealerReceiveCard(dealer)
        }

        BlackjackOutputView.printCardResult(participants)
        BlackjackOutputView.printGameResult(participants)
    }

    private fun Player.action() {
        while (canReceiveCard(isHit())) {
            receiveCard(Deck.draw())
            BlackjackOutputView.printCards(this)
        }
    }

    private fun Player.isHit(): Boolean {
        return BlackjackInputView.readCardReceiveInput(name)
    }
}
