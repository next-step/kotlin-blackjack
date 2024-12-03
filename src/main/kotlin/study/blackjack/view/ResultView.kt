package study.blackjack.view

import study.blackjack.model.BlackjackPlayer

/**
 * @author 이상준
 */
class ResultView {

    fun printInitGiveCardsMessage(players: List<BlackjackPlayer>, cardCount: Int) {
        println(players.joinToString(", ") { it.name })
        println()
        println("${players.joinToString(", ") { it.name }} 에게 ${cardCount}장의 카드를 나누었습니다.")
    }

    fun printInitCardOfPlayer(player: BlackjackPlayer) {
        println("${player.name} 카드: ${player.cards.joinToString(", ") { it.name() }}")
    }

    fun printResultCardOfPlayer(player: BlackjackPlayer) {
        println("${player.name} 카드: ${player.cards.joinToString(", ") { it.name() }} - 결과: ${player.calculateScore()}")
    }

}
