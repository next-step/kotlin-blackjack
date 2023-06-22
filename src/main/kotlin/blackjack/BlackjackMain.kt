package blackjack

import blackjack.domain.BlackJackTable
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

const val YES = "y"

fun main() {
    val players = InputView.getInputPlayers()

    players.forEach {
        BlackJackTable.giveCardsToPlayer(it, BlackJackTable.START_CARD_SIZE)
    }
    OutputView.showPlayerSet(players)

    players.forEach {
        inputMoreCard(it)
    }

    players.forEach {
        OutputView.showGameResult(it)
    }
}

fun inputMoreCard(player: Player) {
    do {
        val yesOrNo = InputView.getInputMoreCards(player.name)
        if (isYes(yesOrNo)) {
            BlackJackTable.giveCardsToPlayer(player)
        }
        OutputView.showPlayerCards(player)
    } while (isYes(yesOrNo))
}

private fun isYes(yesOrNo: String) = yesOrNo.lowercase() == YES
