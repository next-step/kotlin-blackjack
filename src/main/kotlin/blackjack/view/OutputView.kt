package blackjack.view

import blackjack.model.trump.Cards
import blackjack.model.score.Score
import blackjack.model.gamer.Dealer
import blackjack.view.ViewUtil.toString

object OutputView {
    fun printFirstDraw(gamerNames: List<String>) {
        println("${gamerNames.joinToString(separator = ",")}에게 ${Cards.INITIAL_DRAW_COUNT}장의 카드를 나누었습니다.")
    }

    fun printPlayer(name: String, cards: Cards) {
        println("${name}카드: ${toString(cards)}")
    }

    fun printResult(name: String, cards: Cards, score: Score) {
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
