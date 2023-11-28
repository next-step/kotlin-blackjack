package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Player

object OutputView {
    private const val DELIMITER = ","

    fun printCards(name: String, cards: List<Card>) {
        println("${name}카드: ${cards.joinToString(DELIMITER)}")
    }

    fun printPlayerScore(player: Player) {
        println("${player.name}카드 : ${player.cards.joinToString(DELIMITER)} - 결과: ${player.score()}")
    }
}
