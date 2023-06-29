package blackjack

import blackjack.domain.BlackJackTable
import blackjack.domain.GameConditionNotify
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {

    val blackJackTable = BlackJackTable()
    val players = InputView.getInputPlayers()
    val blackJackPlayers = Players(players)

    blackJackTable.startGame(
        blackJackPlayers,
        object : GameConditionNotify {
            override fun giveDefaultCardsToPlayerDone(players: Players) {
                OutputView.showPlayerSet(players)
            }

            override fun isNeedMoreCard(player: Player): Boolean {
                return InputView.isMoreCard(player)
            }

            override fun giveCardToPlayerDone(player: Player) {
                OutputView.showPlayerCards(player)
            }

            override fun finishBlackJackGame(players: Players) {
                OutputView.showGameResult(players)
            }
        }
    )
}
