package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Participant
import blackjack.domain.Player
import blackjack.view.BlackjackInputView
import blackjack.view.BlackjackOutputView

object BlackjackController {
    fun handle() {
        val namesInput = BlackjackInputView.readPlayerNamesInput()
        val players = namesInput.map { Player(it) }
        val participants = listOf(Dealer) + players

        drawInitialCards(participants)
        BlackjackOutputView.printInitialCards(participants)

        players.forEach { action(it) }

        if (Dealer.shouldReceiveCard()) {
            Dealer.receiveCard(Deck.draw())
            BlackjackOutputView.printDealerReceiveCard()
        }

        BlackjackOutputView.printCardResult(participants)
    }

    private fun drawInitialCards(participants: List<Participant>) {
        participants.forEach {
            it.receiveCard(Deck.draw())
            it.receiveCard(Deck.draw())
        }
    }

    private fun action(player: Player) {
        while (!player.isBust() && player.isHit()) {
            player.receiveCard(Deck.draw())
            BlackjackOutputView.printCards(player)
        }
    }

    private fun Player.isHit(): Boolean {
        return BlackjackInputView.readCardReceiveInput(this.name)
    }
}
