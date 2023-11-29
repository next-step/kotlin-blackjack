package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Player

object OutputView {
    private const val DELIMITER = ", "

    fun printCards(name: String, cards: List<Card>) {
        println("${name}카드: ${cards.joinToString(DELIMITER)}")
    }

    fun printDrawTwoCards(players: List<Player>) {
        println("${players.joinToString(DELIMITER) { it.name }}에게 2장의 카드를 나누었습니다.")
        players.forEach { printCards(it.name, it.cards) }
    }

    fun printPlayerScore(player: Player) {
        println("${player.name}카드 : ${player.cards.joinToString(DELIMITER)} - 결과: ${player.score()}")
    }
}
