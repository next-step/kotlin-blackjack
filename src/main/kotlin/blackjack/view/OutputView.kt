package blackjack.view

import blackjack.model.player.Player

object OutputView {
    fun printPlayerInitStatus(players: List<Player>) {
        val names = players.joinToString { it.name }
        println("\n${names}에게 2장의 카드를 나누었습니다.")
        players.forEach { player -> printPlayerCards(player) }
    }

    fun printPlayerCards(player: Player) {
        val card = player.cards.joinToString(separator = ", ") { "${it.number.cardName}${it.suit.name}" }
        println("${player.name} 카드 : $card")
    }
}
