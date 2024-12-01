package blackjack

import blackjack.domain.BlackjackPlayer
import blackjack.domain.Dealer
import blackjack.domain.Player
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
        checkDraw(player, dealer)
        ResultView.printPlayerCardInfo(player)
    }
    ResultView.printResult(players)
}

private fun checkDraw(player: Player, dealer: Dealer) {
    while (InputView.checkDraw(player.name)) {
        dealer giveCardTo player
        ResultView.printPlayerCardInfo(player)
    }
}
