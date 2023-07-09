package blackjack.view

import blackjack.domain.Hands
import blackjack.domain.Player

object ResultView {
    fun init(players: List<Player>) {
        println("${players.joinToString(", ") { it.name }}에게 ${Hands.INIT_CARD_SIZE}장의 카드를 나누었습니다.")

        players.forEach { println("${it.name}카드: ${it.hands.cards.joinToString(",") { "${it.symbol}_${it.type}" }}") }
    }
}