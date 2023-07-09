package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant

object OutputView {
    fun printStart(participants: List<Participant>) {
        val playerNames = participants.joinToString(", ") { it.name }
        println("$playerNames 에게 2장의 카드를 나누었습니다.")
        for (participant in participants) {
            printCards(participant.name, participant.cards())
        }
    }

    fun printCards(name: String, cards: List<Card>) {
        println(resultMessage(name, cards))
    }

    fun printDealerHit(dealer: Dealer) {
        println("${dealer.name}은 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printPlayerResult(participant: Participant) {
        println("${resultMessage(participant.name, participant.cards())} - 결과: ${participant.score().value}")
    }

    private fun resultMessage(name: String, cards: List<Card>): String {
        val cardsMessage =
            cards.joinToString(", ") { DisplayName.cardNumber(it.number) + DisplayName.cardShape(it.shape) }
        return "${name}의 카드: $cardsMessage"
    }

    fun printProfit(name: String, profit: Double) {
        println("$name: $profit")
    }
}
