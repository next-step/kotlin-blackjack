package blackjack.ui

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.WinningResult

object UI {

    fun drawFirstTurnMessage(dealer: Dealer, players: Players) {
        val playerNames = players.list.joinToString(",") { it.name }
        println("${dealer.name}와 ${playerNames}에게 각자 2장을 나누었습니다.")
    }

    fun drawCardList(player: Player) {
        val playerCards = player.currentCards().joinToString(", ") { it.toString() }
        println("${player.name}카드: $playerCards")
    }

    fun drawDealerDrawMessage(dealer: Dealer) {
        println("${dealer.name}는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun drawResult(player: Player) {
        val playerCards = player.currentCards().joinToString(", ") { it.toString() }
        println("${player.name}카드: $playerCards - ${player.score.value}")
    }

    fun drawRecordTitle() {
        println("## 최종 승패")
    }

    fun drawRecord(result: WinningResult) {
        val recordString = when (result.player) {
            is Dealer -> {
                "${result.winCount}승 ${result.loseCount}패 ${result.drawCount}무"
            }
            else -> when {
                result.winCount == 1 -> "승"
                result.loseCount == 1 -> "패"
                result.drawCount == 1 -> "무"
                else -> "무"
            }
        }

        println("${result.player.name}: $recordString")
    }

    fun drawDivider() {
        println()
    }
}
