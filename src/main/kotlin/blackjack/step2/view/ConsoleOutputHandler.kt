package blackjack.step2.view

import blackjack.step2.domain.Card
import blackjack.step2.domain.Dealer
import blackjack.step2.domain.Participant
import blackjack.step2.domain.Player
import blackjack.step2.domain.ProfitResult

object ConsoleOutputHandler {
    fun printInitialCards(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println("딜러와 ${players.joinToString(", ") { it.name }}에게 2장의 카드를 나누었습니다.")
        // 딜러는 첫 카드만 출력
        println("${dealer.name}: ${formatCard(dealer.cards.all[0])}")
        // 플레이어는 전체 카드 출력
        players.forEach { printParticipantCards(it) }
    }

    fun printFinalCards(participants: List<Participant>) {
        participants.forEach { this.printParticipantCards(it) }
    }

    fun printResults(results: List<ProfitResult>) {
        println("## 최종 수익")
        results.forEach { result ->
            val profit = result.profit
            println("${result.participant.name}: ${profit.formatProfit()}")
        }
    }

    private fun Double.formatProfit(): String {
        return if (this % 1.0 == 0.0) this.toInt().toString() else this.toString()
    }

    private fun printParticipantCards(participant: Participant) {
        val cardsString =
            participant.cards.all
                .joinToString(", ") { formatCard(it) }

        println("${participant.name}카드: $cardsString - 결과: ${participant.score()}")
    }

    private fun formatCard(card: Card): String {
        return "${card.number.denomination}${card.type.value}"
    }
}
