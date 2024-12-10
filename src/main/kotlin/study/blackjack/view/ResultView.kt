package study.blackjack.view

import study.blackjack.model.BlackjackPlayer
import study.blackjack.model.Match

/**
 * @author 이상준
 */
class ResultView {
    fun printInitGiveCardsMessage(
        players: List<BlackjackPlayer>,
        cardCount: Int,
    ) {
        println(players.joinToString(", ") { it.name() })
        println()
        println("딜러와 ${players.joinToString(", ") { it.name() }} 에게 ${cardCount}장의 카드를 나누었습니다.")
    }

    fun printInitCardOfPlayer(player: BlackjackPlayer) {
        println("${player.name()} 카드: ${player.cards().toList().joinToString(", ") { it.cardRank.name }}")
    }

    fun printResultCardOfPlayer(player: BlackjackPlayer) {
        val cards = player.cards().toList().joinToString(", ") { it.cardRank.name }
        val score = player.cards().calculateScore()
        println("${player.name()} 카드: $cards - 결과: $score")
    }

    fun printAddCardOfDealerMessage() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        println()
    }

    fun printFinalStats(players: List<BlackjackPlayer>) {
        println()
        println("## 최종 승패")
        val win = players.count { it.result() == Match.LOSE.text }
        val lose = players.count { it.result() == Match.WIN.text }
        println("딜러: ${win}승 ${lose}패")
        players.forEach {
            println("${it.name()}: ${it.result()}")
        }
    }
}
