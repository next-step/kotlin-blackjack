package blackjack

import blackjack.domain.BlackJackTable
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.ViewCallback

fun main() {
    val players = InputView.getInputPlayers()

    BlackJackTable().startGame(
        players,
        object : ViewCallback {
            override fun isMoreCard(player: Player): Boolean {
                return InputView.isMoreCard(player)
            }

            override fun showPlayerSet(players: Players) {
                OutputView.showPlayerSet(players)
            }

            override fun showPlayerCards(player: Player) {
                OutputView.showPlayerCards(player)
            }

            override fun showGameResult(player: Player) {
                OutputView.showGameResult(player)
            }
        }
    )
}
