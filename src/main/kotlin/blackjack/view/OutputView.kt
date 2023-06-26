package blackjack.view

import blackjack.domain.player.Participants
import blackjack.domain.player.PlayerImpl

object OutputView {
    fun printGameStart(names: List<String>, initialDraw: Int) {
        println("\n${names.joinToString(", ")}에게 ${initialDraw}장을 나누었습니다.")
    }

    fun printPlayerCard(players: Participants) {
        players.players.forEach {
            printPlayerCard(it)
        }
        println()
    }

    fun printPlayerCard(player: PlayerImpl) {
        println("${player.name}카드: ${player.cardHold.cards.joinToString(", ") { it.rank.mark + it.shape.mark } }")
    }

    fun printPlayerResult(players: Participants) {
        println()
        players.players.forEach {
            printPlayerResult(it)
        }
    }

    private fun printPlayerResult(player: PlayerImpl) {
        println("${player.name}카드: ${player.cardHold.cards.joinToString(", ") { it.rank.mark + it.shape.mark } } - 결과: ${player.getPoints()}")
    }
}
