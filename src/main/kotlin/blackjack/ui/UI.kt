package blackjack.ui

import blackjack.domain.BetResult
import blackjack.domain.Dealer
import blackjack.domain.Players
import blackjack.domain.User

object UI {

    fun drawFirstTurnMessage(dealer: Dealer, players: Players) {
        val playerNames = players.list.joinToString(",") { it.name }
        println("${dealer.name}와 ${playerNames}에게 각자 2장을 나누었습니다.")
    }

    fun drawCardList(user: User) {
        val playerCards = user.openedCards().joinToString(", ") { it.toString() }
        println("${user.name}카드: $playerCards")
    }

    fun drawDealerDrawMessage(dealer: Dealer) {
        println("${dealer.name}는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun drawResult(user: User) {
        val playerCards = user.currentCards().joinToString(", ") { it.toString() }
        println("${user.name}카드: $playerCards - ${user.score.value}")
    }

    fun drawRecordTitle() {
        println("## 최종 수익")
    }

    fun drawResult(result: BetResult) {
        println("${result.user.name}: ${result.earnMoney}")
    }

    fun drawDivider() {
        println()
    }
}
