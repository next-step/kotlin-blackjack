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
    ResultView.resultReady(blackJack.players)
    blackJack.players.forEach { playerCheckBust(it, blackJack) }
    ResultView.resultGame(blackJack.players)
}

fun playerCheckBust(player: Player, blackJack: BlackJack){
    isGetCard = true
    while (!player.isBust() && isGetCard) {
        playerWhetherGet(player, blackJack)
    }
}

fun playerWhetherGet(player: Player, blackJack: BlackJack) {
    val inputValue = InputView.inputWhether(player)
    if (inputValue == "y") {
        blackJack.giveCardPlayer(player)
        ResultView.resultWhetherBust(player)
    }
    if (inputValue == "n") {
        ResultView.resultPlayerHands(player)
        isGetCard = false
    }
}
