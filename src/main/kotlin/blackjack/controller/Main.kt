package blackjack.controller

import blackjack.domain.BlackJack
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val players = InputView.getNames()
    BlackJack.start(players)
    ResultView.printStart(players)

    players.forEach {
        play(it)
    }
}

fun play(player: Player) {
    var playCount = 0
    while (BlackJack.canPlay(player) && InputView.getAnswer(player) == "y") {
        player.draw()
        ResultView.printCards(player)
        playCount++
    }
    if (playCount == 0) {
        ResultView.printCards(player)
    }
}
