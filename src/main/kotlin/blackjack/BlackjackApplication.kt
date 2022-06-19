package blackjack

import blackjack.domain.PlayGame
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()

    val names = inputView.getNames()
    val players = names.map(::Player)

    val playGame = PlayGame()
    players.forEach { player ->
        playGame.start(player)
    }
    outputView.firstCard(players)

    players.forEach { player ->
        var hit: Boolean
        runCatching {
            do {
                hit = inputView.hitOrStand(player.name)
                if (hit) {
                    playGame.hit(player)
                }
                outputView.cardOfPlayer(player)
            } while (hit)
        }.onFailure { println(it.message) }
    }

    outputView.result(players)
}
