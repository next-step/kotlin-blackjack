package blackjack.view

import blackjack.model.trump.Cards
import blackjack.model.Players
import blackjack.model.Score
import blackjack.view.ViewUtil.toString

object OutputView {
    fun printFirstDraw(players: Players) {
        println("${players.joinToString(", ") { it.name }}에게 ${Cards.INITIAL_DRAW_COUNT}장의 카드를 나누었습니다.")
        players.forEach { player ->
            println("${player.name}카드: ${toString(player.cards)}")
        }
    }

    fun printResult(name: String, cards: Cards, score: Score) {
        println("${name}카드: ${toString(cards)} - 결과: ${score.value}")
    }
}
