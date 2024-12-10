package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.view.InputView

class BlackjackController(private val inputView: InputView) {
    fun start() {
        val playerNames = inputView.getPlayerNames()
        println(playerNames)

        val deck = createDeck()
        val dealer = createDealer(deck)
        val players = createPlayers(playerNames)
    }

    private fun createDeck() = Deck()

    private fun createDealer(deck: Deck) = Dealer(deck)

    private fun createPlayers(playerNames: List<String>) = playerNames.map { Player(it) }
}
