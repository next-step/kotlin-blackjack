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

        val playersResult = game.scratchPlayers(
            players,
            inputScratch = { inputView.inputScratch(it) },
            printPlayerCards = { outputView.printPlayerCards(it) }
        )
        println()
        val dealerResult =
            game.scratchDealer(
                dealer,
                printDealerDrawOneMoreCard = { outputView.printDealerDrawOneMoreCard() }
            )

        outputView.printResultCards(dealerResult, playersResult)
        outputView.printGameResult(dealerResult, playersResult)
    }
}

fun main() {
    BlackjackController().playGame()
}
