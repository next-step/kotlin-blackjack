package blackjack.view

import blackjack.domain.user.Dealer
import blackjack.domain.user.Player

class ConsoleResultView : ResultView {
    override fun printPlayerCards(player: Player) {
        println("${player.name}카드: ${player.cards.toList()}")
    }

    override fun printResult(player: Player) {
        println("${player.name} ${player.cards.toList()} 결과: ${player.cards.getScore().value}")
    }

    override fun printFinalResult(player: Player) {
        val finalResult = player.getFinalResult()

        if (player is Dealer) {
            println("${player.name}: ${finalResult.winCount} 승 ${finalResult.loseCount} 패")
            return
        }

        val resultString = when {
            finalResult.winCount == 1 -> "승"
            finalResult.loseCount == 1 -> "패"
            else -> "무"
        }

        println("${player.name}: $resultString")
    }

    override fun printDealerDrawCardAlert(dealerDrawThresholdPoint: Int) {
        println("dealer가 $dealerDrawThresholdPoint 이하라 한장의 카드를 더 받았습니다")
    }
}
