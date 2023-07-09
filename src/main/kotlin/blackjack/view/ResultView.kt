package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.domain.Participants

object ResultView {
    fun initialCards(participants: Participants) {
        println("딜러와 ${participants.toList().joinToString(", ") { it.name }}에게 2장의 나누었습니다.")
        participants.forEach {
            if (it is Dealer) {
                dealerAndCards(it)
                return@forEach
            }
            participantAndCards(it)
        }
    }

    private fun dealerAndCards(participant: Participant) {
        println("딜러: ${participant.cards.cards.first()}")
    }

    fun participantAndCards(participant: Participant) {
        println("${participant.name}카드: ${participant.cards.cards.joinToString(", ")}")
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
