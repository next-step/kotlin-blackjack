package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.GameResult
import blackjack.domain.Participants
import blackjack.domain.Player
import blackjack.view.BlackjackInputView
import blackjack.view.BlackjackOutputView

class BlackjackController(
    private val dealer: Dealer,
    private val deck: Deck,
) {
    fun handle() {
        val namesInput = BlackjackInputView.readPlayerNamesInput()
        val players = namesInput.map { Player(it) }
        val participants = Participants(dealer, players)

        participants.drawInitialCards(deck)
        BlackjackOutputView.printInitialCards(participants)

        players.forEach { it.action(deck) }

        if (dealer.shouldReceiveCard()) {
            dealer.receiveCard(deck.draw())
            BlackjackOutputView.printDealerReceiveCard(dealer)
        }

        val gameResult = GameResult(participants.players, participants.dealer)

        BlackjackOutputView.printCardResult(participants)
        BlackjackOutputView.printGameResult(participants, gameResult)
    }

    private fun Player.action(deck: Deck) {
        while (canReceiveCard() && isHit()) {
            receiveCard(deck.draw())
            BlackjackOutputView.printCards(this)
        }
    }

    private fun Player.isHit(): Boolean {
        return BlackjackInputView.readCardReceiveInput(name)
    }
}
