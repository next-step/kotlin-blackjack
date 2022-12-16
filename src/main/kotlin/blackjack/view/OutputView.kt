package blackjack.view

import blackjack.domain.Player

class OutputView {

    fun printSetUp(players: List<Player>) {
        val cardSize = players[0].cards.size
        val playerNames = players.map { it.name }.joinToString(", ")
        println("${playerNames}에게 ${cardSize}장의 카드를 나누었습니다.")

        players.forEach {
            printEachPlayer(it)
        }
    }

    private fun printEachPlayer(player: Player) {
        val cards = player.cards.map { "${it.cardNumber.displayName}${it.cardSuit.displayName}" }.joinToString(", ")
        println("${player.name}카드: $cards")
    }
}
