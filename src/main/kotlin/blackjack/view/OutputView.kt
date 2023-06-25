package blackjack.view

import blackjack.Player

object OutputView {
    fun initialCardCasting(players: List<Player>, castedCard: Int) {
        val playerNames = players.joinToString(separator = ", ") { it.name }
        println("${playerNames}에게 ${castedCard}장을 나누었습니다.")
        players.forEach {
            val playerCardNames = it.cards.joinToString(separator = ", ") { card -> card.toString() }
            println("${it.name}카드: $playerCardNames")
        }
    }
}
