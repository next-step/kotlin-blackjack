package blackjack.view

import blackjack.domain.user.Player

class ConsoleResultView : ResultView {
    override fun printPlayerCards(player: Player) {
        println("${player.name}카드: ${player.cards.toList()}")
    }

    override fun printResult(player: Player) {
        println("${player.name} ${player.cards.toList()} 결과: ${player.cards.getOptimizedPoint()}")
    }
}
