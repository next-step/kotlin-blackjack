package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Game
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(private val inputView: InputView, private val outputView: OutputView) {
    fun start() {
        val playerNames = inputView.getPlayerNames()

        val deck = createDeck()
        val dealer = createDealer(deck)
        val players = createPlayers(playerNames)
        val game = Game(dealer, players)

        game.start()

        outputView.dealResult(players)
    }

    private fun createDeck() = Deck()

    private fun createDealer(deck: Deck) = Dealer(deck)

    private fun createPlayers(playerNames: List<String>) = Players(playerNames.map { Player(it) })
}
