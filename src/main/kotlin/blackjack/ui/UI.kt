package blackjack.ui

import blackjack.domain.BetResult
import blackjack.domain.Player
import blackjack.domain.Players

object UI {

    fun drawFirstTurnMessage(dealer: Player, players: Players) {
        val playerNames = players.list.joinToString(",") { it.name }
        println("${dealer.name}와 ${playerNames}에게 각자 2장을 나누었습니다.")
    }

    fun drawCardList(player: Player) {
        val playerCards = player.openedCards().joinToString(", ") { it.toString() }
        println("${player.name}카드: $playerCards")
    }

    fun drawDealerDrawMessage(dealer: Player) {
        println("${dealer.name}는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun drawResult(player: Player) {
        val playerCards = player.currentCards().joinToString(", ") { it.toString() }
        println("${player.name}카드: $playerCards - ${player.score.value}")
    }

    fun drawRecordTitle() {
        println("## 최종 수익")
    }

    fun drawResult(result: BetResult) {
        println("${result.player.name}: ${result.earnMoney}")
    }

    fun drawDivider() {
        println()
    }
}
