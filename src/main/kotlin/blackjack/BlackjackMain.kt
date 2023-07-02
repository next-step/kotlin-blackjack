package blackjack

import blackjack.domain.BlackJackTable
import blackjack.domain.BlackJackTableBuilder
import blackjack.domain.GameConditionNotify
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerNotify
import blackjack.domain.player.Players
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {

    val playerNotify = object : PlayerNotify {
        override fun inputPlayer(): MutableList<Player> {
            return InputView.getInputPlayers()
        }

        override fun inputBettingMoney(players: MutableList<Player>) {
            InputView.setBettingMoney(players)
        }
    }

    val blackJackTable = blackjackTable {
        cardDeck = makeCardDeck()
        notify = playerNotify
        joinPlayers {
            setBettingMoney()
        }
    }

    blackJackTable.startGame(object : GameConditionNotify {
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
    })
}

fun blackjackTable(block: BlackJackTableBuilder.() -> Unit): BlackJackTable {
    return BlackJackTableBuilder().apply(block).build()
}
