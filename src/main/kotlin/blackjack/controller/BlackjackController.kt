package blackjack.controller

import blackjack.domain.Game
import blackjack.view.ConsoleInput
import blackjack.view.ConsoleOutput

class BlackjackController {
    private val inputView = ConsoleInput
    private val outputView = ConsoleOutput

    fun playGame() {
        val game = Game(inputView.inputPlayersInfo())
        val dealer = game.getDealer()
        val players = game.initialCard()
        outputView.printInitialCards(dealer, players)

        val playersResult = game.playPlayers(
            players = players,
            inputScratch = { inputView.inputScratch(it) },
            printPlayerCards = { outputView.printPlayerCards(it) }
        )

        val dealerResult = game.playDealer(dealer).also { outputView.printDealerDrawOneMoreCard(it) }

        val gameResult = game.finish(dealerResult, playersResult)
        outputView.printGameResult(gameResult)
    }
}

fun main() {
    BlackjackController().playGame()
}
