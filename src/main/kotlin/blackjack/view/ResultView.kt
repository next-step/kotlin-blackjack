package blackjack.view

import blackjack.domain.Participant

object ResultView {

    fun handingOutCards(participants: List<Participant>) {
        val names = participants.joinToString { it.name }
        println("${names}에게 2장의 나누었습니다.")
    }
}
