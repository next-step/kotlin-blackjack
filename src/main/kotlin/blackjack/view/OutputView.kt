package blackjack.view

import blackjack.model.Players

object OutputView {
    fun printFirstDraw(players: Players) {
        println("${players}에게 ${players.first().cards.size}장의 카드를 나누었습니다.")
        players.forEach {
            println("${it}카드: ${it.cards}")
        }
    }
}
