package blackjack.view

import blackjack.domain.Participant
import blackjack.domain.Player

object ResultView {

    fun handingOutCards(participants: List<Participant>) {
        val names = participants.joinToString { it.name }
        println("${names}에게 2장의 나누었습니다.")
    }

    fun getCurrentStatus(vararg player: Player) {
        player.forEach {
            println("${it.name.name}카드: ${it.cards.joinToString { card -> "${card.number.number}${card.type.text}" }}")
        }
    }
}
