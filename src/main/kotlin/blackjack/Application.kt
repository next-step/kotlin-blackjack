package blackjack

import blackjack.domain.BlackjackPlayer
import blackjack.domain.Dealer
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val dealer = Dealer()

    val playerNames = InputView.inputPlayerNames()
    val players = playerNames.map {
        dealer initCardTo BlackjackPlayer(name = it)
    }
    ResultView.printPlayersInitInfo(players)

    players.forEach { player ->
        while (InputView.checkDraw(player.name) == "y") {
            dealer giveCardTo player
            ResultView.printPlayerCardInfo(player)
        }
        ResultView.printPlayerCardInfo(player)
    }
    ResultView.printResult(players)
}
