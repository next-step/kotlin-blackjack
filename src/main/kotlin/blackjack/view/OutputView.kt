package blackjack.view

import blackjack.domain.entity.Player

object OutputView {

    private const val SET_PLAY_MESSAGE = "에게 2장의 카드를 나누었습니다."
    private const val CARD = "카드"
    private const val RESULT = "결과"

    fun printSettingPlayer(players: List<Player>) {
        println("$players $SET_PLAY_MESSAGE")
        println()
        players.forEach { println("${it.name} $CARD : ${it.cards}") }
    }

    fun printPlayCard(player: Player) {
        println("${player.name} $CARD : ${player.cards}")
    }

    fun gameEnd(players: List<Player>) {
        players.forEach { println("${it.name} $CARD: ${it.cards} - $RESULT: ${it.scoreCalculation()}") }
    }
}
