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
        val blackjackGame = initializeGame()
    }

    private fun initializeGame(): Game {
        val playerNames = inputView.getPlayerNames()
        val players = createPlayers(playerNames)
        return Game(dealer = createDealer(), players = players)
    }

    private fun createDealer() = Dealer(Deck())

    private fun createPlayers(playerNames: List<String>) = Players(playerNames.map { Player(it) })
}
