package blackjack.view

import blackjack.domain.entity.Player
import blackjack.domain.util.PlayerCardCalculation

object OutputView {

    private const val SET_PLAY_MESSAGE = "에게 2장의 카드를 나누었습니다."

    fun printSettingPlayer(players: List<Player>) {
        println("$players $SET_PLAY_MESSAGE")
        println()
        players.forEach { println("${it.name}카드 : ${it.cards}") }
    }

    fun printPlayCard(player: Player) {
        println("${player.name}카드 : ${player.cards}")
    }

    fun gameEnd(players: List<Player>) {
        players.forEach { println("${it.name}카드: ${it.cards} - 결과: ${PlayerCardCalculation.calculation(it)}") }
    }
}
