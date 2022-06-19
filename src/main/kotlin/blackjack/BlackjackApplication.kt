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
            while (true) {
                if (!player.canHit()) {
                    println("${player.name}의 카드가 21 이상입니다. 카드를 더 받을 수 없습니다. \n")
                    break
                }
                val hit = InputView.hitOrStand(player.name)
                if (hit) {
                    playGame.hit(player)
                }
                OutputView.cardOfPlayer(player)
                if (!hit) break
            }
        }.onFailure { println(it.message) }
    }

    OutputView.result(players)
}
