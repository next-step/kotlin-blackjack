package blackjack.ui

import blackjack.domain.Player

class OutputView {
    fun printPlayerCardList(player: Player) {
        println("${player.name}카드 : ${player.getCardList()}")
    }

    fun printGameResult(player: Player) {
        println("${player.name}카드 : ${player.getCardList()} - 결과: ${player.calculateCards()}")
    }
}
