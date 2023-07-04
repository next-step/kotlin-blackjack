package blackjack.view

import blackjack.domain.user.Player

class ConsoleResultView : ResultView {
    override fun printPlayerCards(player: Player) {
        println("${player.name}카드: ${player.cards.toList()}")
    }

    override fun printResult(player: Player) {
        println("${player.name} ${player.cards.toList()} 결과: ${player.cards.getOptimizedPoint()}")
    }

    override fun printDealerDrawCardAlert(dealerDrawThresholdPoint: Int) {
        println("dealer가 $dealerDrawThresholdPoint 이하라 한장의 카드를 더 받았습니다")
    }
}
