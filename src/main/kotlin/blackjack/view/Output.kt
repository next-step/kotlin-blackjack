package blackjack.view

import blackjack.domain.BlackJack
import blackjack.domain.Player

object Output {

    fun initResult(players: List<Player>) {
        println("${players.joinToString(",") { it.name }}에게 카드 두장을 나누어 주었습니다.")
        players.forEach {
            println("${it.name} 카드 : ${it.cardsName()}")
        }
    }

    fun result(players: List<Player>) {
        players.forEach {
            println("${it.name}가 뽑은 카드 : ${it.cardsName()} - 점수 : ${it.score}")
        }
    }

    fun winner(blackJack: BlackJack) {
        if (!blackJack.winner().isNullOrEmpty()) {
            println("${blackJack.winner().joinToString(",")}가 승리했습니다.")
        }
    }

    fun winnerOrOver21() {
        println("더 이상 뽑을 수 없습니다.")
    }
}
