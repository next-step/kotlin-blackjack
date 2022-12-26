package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Players
import blackjack.domain.User
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackController {
    fun run() {
        val names = InputView.inputPlayersName()
        val users = names.map { User(it) }
        val game = BlackJackGame(deck = Deck(), dealer = Dealer(), players = Players(users))

        game.drawInitCards()
        ResultView.printInitialStatus(game)

        game.play(
            inputIsGetCard = { InputView.inputIsGetCard(it) },
            printPlayerStatus = { ResultView.printPlayerStatus(it) }
        )
        game.playDealer { ResultView.printDealerHitOrStay(it) }

        val playerResults = game.getPlayerResults()
        ResultView.printStatus(game)
        ResultView.printResults(playerResults, game.dealer)
    }
}

fun main() {
    BlackjackController().run()
}
