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
        val deck = Deck()

        drawInitialCards(participants, deck)

        BlackjackOutputView.printInitialCards(participants)

        players.forEach { action(it, deck) }

        BlackjackOutputView.printResult(participants)
    }

    private fun drawInitialCards(participants: List<Participant>, deck: Deck) {
        participants.forEach {
            it.receiveCard(deck.draw())
            it.receiveCard(deck.draw())
        }
    }

    private fun action(player: Player, deck: Deck) {
        while (!player.isFinished() && player.isHit()) {
            player.receiveCard(deck.draw())
            BlackjackOutputView.printCards(player)
        }
    }

    private fun Player.isHit(): Boolean {
        return BlackjackInputView.readCardReceiveInput(this.name)
    }
}
