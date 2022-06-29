package blackjack

import blackjack.domain.PlayGame
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val names = InputView.getNames()
    val players = names.map(::Player)

    val playGame = PlayGame()
    players.forEach { player ->
        playGame.start(player)
    }
    OutputView.firstCard(players)

    players.forEach { player ->
        runCatching {
            playGame.hitOrStandMultipleTimes(player)
        }.onFailure { println(it.message) }
    }

    OutputView.result(players)
}
