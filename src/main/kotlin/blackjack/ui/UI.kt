package blackjack.ui

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Players

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

    fun drawRecord(player: Player) {
        var recordString = ""

        when {
            player.winningRecord.winCount == 1 -> recordString += "승 "
            player.winningRecord.winCount > 1 -> recordString += "${player.winningRecord.winCount}승 "
        }

        when {
            player.winningRecord.loseCount == 1 -> recordString += "패 "
            player.winningRecord.loseCount > 1 -> recordString += "${player.winningRecord.loseCount}패 "
        }

        println("${player.name}: $recordString")
    }

    fun drawDivider() {
        println()
    }
}
