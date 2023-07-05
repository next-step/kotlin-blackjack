package blackjack

import blackjack.domain.BlackJackTable
import blackjack.domain.GameConditionNotify
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerNotify
import blackjack.domain.player.Players
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {

    val blackJackTable = BlackJackTable.of(object : PlayerNotify {
        override fun generatePlayers(): List<Player> {
            return InputView.getInputPlayers()
        }
    })

    blackJackTable.startGame(object : GameConditionNotify {
        override fun giveDefaultCardsToPlayerDone(players: Players) {
            OutputView.showPlayerSet(players)
        }

        override fun isNeedMoreCard(player: Player): Boolean {
            return InputView.inputIsMoreCard(player)
        }

        override fun giveCardToPlayerDone(player: Player) {
            OutputView.showPlayerCards(player)
        }

        override fun finishBlackJackGame(players: Players) {
            OutputView.showGameResult(players)
        }
    })
}
