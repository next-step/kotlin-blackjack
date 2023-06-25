package blackjack

import blackjack.domain.BlackJackTable
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.GameConditionNotify
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {

    val blackJackTable = BlackJackTable()
    val blackJackPlayers = InputView.getInputPlayers(blackJackTable)
    blackJackTable.startGame(
        blackJackPlayers,
        object : GameConditionNotify {

            override fun showPlayerCardSet(players: Players) {
                OutputView.showPlayerSet(players)
            }

            override fun isNeedMoreCard(player: Player): Boolean {
                return InputView.isMoreCard(player)
            }

            override fun showPlayerCards(player: Player) {
                OutputView.showPlayerCards(player)
            }

            override fun showGameResult(players: Players) {
                OutputView.showGameResult(players)
            }
        }
    )
}
