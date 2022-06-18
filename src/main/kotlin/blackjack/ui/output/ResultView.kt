package blackjack.ui.output

import blackjack.domain.participant.Money
import blackjack.domain.participant.Participant
import blackjack.domain.result.BlackJackResult

object ResultView {

    fun showResultHand(participants: List<Participant>) {
        participants.forEach {
            println(it.display())
        }
        println()
    }

    private fun Participant.display(): String {
        return "${name}카드: $hand - 결과: ${point().value}"
    }

    fun showBlackJackResult(blackJackResult: BlackJackResult) {
        println("## 최종 수익")
        blackJackResult.participantResults.forEach {
            println("${it.playerName}: ${it.profit.display()}")
        }
    }

    private fun Money.display(): Int {
        return this.amount
    }
}
