package blackjack.view

import blackjack.model.Rule
import blackjack.model.trump.Cards
import blackjack.model.score.Score
import blackjack.model.gamer.Dealer
import blackjack.model.gamer.Gamer
import blackjack.view.ViewUtil.toString

object OutputView {
    fun printFirstDraw(gamers: List<Gamer>) {
        println("${gamers.joinToString(", ") { it.name }}에게 ${Cards.INITIAL_DRAW_COUNT}장의 카드를 나누었습니다.")
        gamers.forEach {
            printPlayer(it)
        }
    }

    fun printPlayer(gamer: Gamer) {
        println("${gamer.name}카드: ${toString(gamer.cards)}")
    }

    fun printResults(gamers: List<Gamer>, rule: Rule) {
        gamers.forEach {
            printResult(it.name, it.cards, rule.getScore(it.cards))
        }
    }

    private fun printResult(name: String, cards: Cards, score: Score) {
        println("${name}카드: ${toString(cards)} - 결과: ${score.value}")
    }

    fun printDealerReason() {
        println("딜러는 ${Dealer.MINIMUM_SCORE.value}이하라 한장의 카드를 더 받았습니다.")
    }

    fun printRevenue(gamerName: String, gamerRevenue: Int) {
        println("$gamerName: $gamerRevenue")
    }

    fun printJudgeTitle() {
        println("## 최종 수익")
    }
}
