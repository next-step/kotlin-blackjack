package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.domain.Participants

object ResultView {
    fun initialCards(participants: Participants) {
        val playerNames = participants.getPlayers().joinToString(", ") { it.name }
        println("딜러와 ${playerNames}에게 2장의 나누었습니다.")
        participants.forEach {
            participantAndInitialCards(it)
        }
    }

    private fun participantAndInitialCards(participant: Participant) {
        val left = if (participant is Dealer) participant.name else "${participant.name}카드"
        println("$left: ${participant.getInitialCards().joinToString(", ")}")
    }

    fun participantAndCards(participant: Participant) {
        val left = if (participant is Dealer) participant.name else "${participant.name}카드"
        println("$left: ${participant.cards.cards.joinToString(", ")}")
    }

    fun result(participants: Participants) {
        participants.forEach {
            println("${it.name}카드: ${it.cards.cards.joinToString(", ")} - 결과: ${it.cards.calculateScore()}")
        }
    }

    fun finalResult(participants: Participants) {
        println("## 최종 승패")
        if (participants.isDealerBust()) {
            println("딜러: 패")
            participants.getPlayers().forEach {
                println("${it.name}: 승")
            }
            return
        }
        val dealerResult = participants.calculateDealerResult()
        println("딜러: ${dealerResult.win}승 ${dealerResult.lose}패")

        participants.getPlayers().forEach {
            val gameResult = it.calculateResult(participants.getDealer())
            println("${it.name}: ${gameResult.result}")
        }
    }
}
