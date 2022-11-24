package blackjack

import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {

    val names = InputView.askNames()
    val deck = CardDeck()

    val game = BlackJackGame(names.map { Player(it) }, deck)
    val playerDtos = game.start()

    OutputView.printFirstDeal(names.joinToString(", "))
    OutputView.printCardsByPlayer(playerDtos)


    names.forEach {
        dealByPlayer(it, game)
    }
    OutputView.printResult(game.result())
}

private fun dealByPlayer(name: String, game: BlackJackGame) {
    do {
        val moreCard: Boolean = InputView.askMoreCard(name)
        if (moreCard) {
            val playerDto = game.addCard(name)
            OutputView.printCardsByPlayer(playerDto)
        }
    } while (moreCard)
}
