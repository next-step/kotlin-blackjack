package blackjack.view

import blackjack.model.trump.Cards
import blackjack.model.player.Players
import blackjack.model.Score
import blackjack.model.player.Dealer
import blackjack.model.player.Player
import blackjack.view.ViewUtil.toString

object OutputView {
    fun printFirstDraw(players: Players) {
        println("${players.joinToString(", ") { it.name }}에게 ${Cards.INITIAL_DRAW_COUNT}장의 카드를 나누었습니다.")
        players.forEach {
            printPlayer(it)
        }
    }

    fun printPlayer(player: Player) {
        println("${player.name}카드: ${toString(player.cards)}")
    }

    fun printResult(name: String, cards: Cards, score: Score) {
        println("${name}카드: ${toString(cards)} - 결과: ${score.value}")
    }

    fun printDealerReason() {
        println("딜러는 ${Dealer.MINIMUM_SCORE.value}이하라 한장의 카드를 더 받았습니다.")
    }

    fun printPlayerJudgeResult(playerName: String, isWin: Boolean) {
        println("$playerName: ${if (isWin) "승" else "패"}")
    }

    fun printDealerJudgeResult(
        dealerName: String,
        dealerWinCount: Int,
        dealerLoseCount: Int
    ) {
        println(
            "$dealerName: ${if (dealerWinCount > 0) "${dealerWinCount}승" else ""} ${if (dealerLoseCount > 0) "${dealerLoseCount}패" else ""}"
        )
    }

    fun printJudgeTitle() {
        println("## 최종 승패")
    }
}
