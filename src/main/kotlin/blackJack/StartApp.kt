package blackJack

import blackJack.domain.BlackJack
import blackJack.domain.Player
import blackJack.view.InputView
import blackJack.view.ResultView

var isGetCard = true

fun main() {
    try {
        startGame()
    } catch (e: Exception) {
        println(e.message)
    }
}

fun startGame() {
    val names = InputView.inputPlayer()
    val blackJack = BlackJack(names)
    ResultView.resultReady(blackJack)
    blackJack.players.forEach { playerCheckBust(it, blackJack) }
    ResultView.resultOpenDealerCard(blackJack.dealer)
    dealerGetCard(blackJack)
    ResultView.resultGame(blackJack)
}

fun playerCheckBust(player: Player, blackJack: BlackJack) {
    isGetCard = true
    while (!player.isBust() && isGetCard) {
        playerWhetherGet(player, blackJack)
    }
}

fun playerWhetherGet(player: Player, blackJack: BlackJack) {
    val inputValue = InputView.inputWhether(player)
    if (inputValue == "y") {
        blackJack.giveCard(player)
        ResultView.resultWhetherBust(player)
    }
    if (inputValue == "n") {
        ResultView.resultPeopleHands(player)
        println()
        isGetCard = false
    }
}

fun dealerGetCard(blackJack: BlackJack) {
    while (!blackJack.dealer.isOver16()) {
        blackJack.giveCard(blackJack.dealer)
        ResultView.resultDealerGetCard(blackJack.dealer)
    }
}
