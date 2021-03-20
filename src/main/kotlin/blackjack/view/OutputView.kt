package blackjack.view

import blackjack.model.Cards
import blackjack.model.Players

object OutputView {
    fun printFirstDraw(players: Players) {
        println("${players}에게 ${Cards.INITIAL_DRAW_COUNT}장의 카드를 나누었습니다.")
        players.forEach {
            println("${it}카드: ${it.cards}")
        }
    }

    fun printResult(players: Players) {
        players.forEach {
            println("${it}카드: ${it.cards} - 결과: ${it.cards.getScore()}")
        }
    }
}
