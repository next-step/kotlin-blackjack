package blackjack

import blackjack.controller.BlackJackGameConsole
import blackjack.model.BlackJackGame
import blackjack.model.participant.Players
import blackjack.view.InputView

fun main() {
    val players = Players(InputView.readPlayerNames()) { InputView.readBetMoney(it) }
    val game = BlackJackGame(players)
    val gameConsole = BlackJackGameConsole(game)

    gameConsole.playGame()
}
