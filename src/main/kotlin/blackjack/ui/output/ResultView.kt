package blackjack.ui.output

import blackjack.domain.Participant

object ResultView {

    fun showResult(participants: List<Participant>) {
        println()
        participants.forEach {
            println("${it.name}카드: ${it.hand} - 결과: ${it.hand.calculate()}")
        }
    }
}
