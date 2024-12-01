package blackjack.view

import blackjack.domain.Player

object ResultView {
    fun printPlayersInitInfo(player: List<Player>) {
        val playerNames = player.joinToString { it.name }

        println("${playerNames}에게 2장의 나누었습니다.")
        player.forEach {
            printPlayerCardInfo(it)
        }
        println()
    }

    fun printPlayerCardInfo(player: Player) {
        println(makeCardInfo(player))
    }

    fun printResult(player: List<Player>) {
        println()
        player.forEach {
            println("${makeCardInfo(it)} - 결과: ${it.score}")
        }
    }

    private fun makeCardInfo(player: Player) = "${player.name}카드: ${player.cardsToString()}"
}
