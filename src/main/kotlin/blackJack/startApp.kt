package blackJack

import blackJack.view.InputView

fun main() {
    try {
        startApp()
    } catch (e: Exception) {
        println(e.message)
    }
}

fun startApp() {
    val players = InputView.playerNames()
    players.players.forEach { it.bettingMoney(InputView.bettingMoney(it)) }
}
