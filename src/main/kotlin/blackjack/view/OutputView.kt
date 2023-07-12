package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant

object OutputView {
    fun printStartGame(participants: List<Participant>) {
        val participantNames = participants.joinToString(", ") { DisplayName.participant(it) }
        val startCardCount = Participant.START_CARD_COUNT
        println("$participantNames 에게 ${startCardCount}장의 카드를 나누었습니다.")

        for (participant in participants) {
            val participantName = DisplayName.participant(participant)
            printCards(participantName, participant.cards())
        }
    }

    fun printCards(name: String, cards: List<Card>) {
        println(cardsMessage(name, cards))
    }

    fun printParticipantScore(participant: Participant) {
        val cards = cardsMessage(DisplayName.participant(participant), participant.cards())
        val score = participant.score().value
        println("$cards - 결과: $score")
    }

    private fun cardsMessage(name: String, cards: List<Card>): String {
        val cards = cards
            .joinToString(", ") {
                DisplayName.cardNumber(it.number) + DisplayName.cardShape(it.shape)
            }
        return "${name}의 카드: $cards"
    }

    fun printDealerHit(dealer: Dealer) {
        val name = DisplayName.participant(dealer)
        println("${name}은 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printProfit(participant: Participant, profit: Double) {
        val name = DisplayName.participant(participant)
        println("$name: $profit")
    }
}
