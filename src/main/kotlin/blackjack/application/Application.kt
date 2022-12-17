package blackjack.application

import blackjack.domain.BlackJackGame
import blackjack.domain.CardGenerator
import blackjack.domain.CardManager
import blackjack.domain.PlayerGenerator
import blackjack.view.InputView
import blackjack.view.OutputView

class Application {

    private val inputView = InputView()
    private val outputView = OutputView()
    private val playerGenerator = PlayerGenerator()
    private val cardManager = CardManager(CardGenerator())

    fun run() {
        val playerNames = inputView.inputPlayers()
        val players = playerGenerator.generate(playerNames)
        val blackJackGame = BlackJackGame(inputView, outputView, cardManager, players)
        blackJackGame.play()
        outputView.printResult(players)
    }
}
