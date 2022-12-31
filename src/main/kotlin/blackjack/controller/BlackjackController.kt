package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Name
import blackjack.domain.User
import blackjack.domain.Users
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackController {
    fun run() {
        val names = InputView.inputPlayersName()
        val users = names.map { name ->
            User(name = Name(name), betAmount = { InputView.inputBetAmount(name) })
        }

        val game = BlackJackGame(deck = Deck(), dealer = Dealer(), users = Users(users))

        game.drawInitCards()
        ResultView.printInitialStatus(game)

        game.playPlayers(
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
