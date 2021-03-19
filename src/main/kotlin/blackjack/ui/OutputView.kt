package blackjack.ui

import blackjack.domain.Player

object OutputView {

    fun printPlayerInfo(players: List<Player>) {
        println("${players.map { it.name }}에게 2장을 나누었습니다.")
        for (player in players) {
            println("${player.name}: ${player.cards.getCardList()}")
        }
    }

    fun printPlayerCardList(player: Player) {
        println("[${player.name}] 카드: ${player.cards.getCardList()}")
    }

    fun printGameResult(players: List<Player>) {
        for (player in players) {
            println("Player[${player.name}] 카드 : ${player.cards.getCardList()} - 결과: ${player.calculateMyCards()}")
        }
    }
}
