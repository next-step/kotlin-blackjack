package blackjack.view

import blackjack.model.Cards
import blackjack.model.Players
import blackjack.model.Rule
import blackjack.view.ViewUtil.toString

object OutputView {
    fun printFirstDraw(players: Players) {
        println("${players.joinToString(", ") { it.name }}에게 ${Cards.INITIAL_DRAW_COUNT}장의 카드를 나누었습니다.")
        players.forEach { player ->
            println("${player.name}카드: ${toString(player.cards)}")
        }
    }

    fun printResult(players: Players) {
        players.forEach { player ->
            println("${player.name}카드: ${toString(player.cards)} - 결과: ${Rule.getScore(player.cards).value}")
        }
    }
}
