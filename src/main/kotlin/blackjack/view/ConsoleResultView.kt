package blackjack.view

import blackjack.domain.user.Player

class ConsoleResultView : ResultView {
    override fun printPlayerCards(player: Player) {
        println("${player.name}카드: ${player.getCards()}")
    }

    override fun printResult(player: Player) {
        println("${player.name} ${player.getCards()} 결과: ${player.getPoint()}")
    }
}
