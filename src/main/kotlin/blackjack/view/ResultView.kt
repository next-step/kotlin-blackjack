package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.FinalResult
import blackjack.domain.Participant
import blackjack.domain.Participants

object ResultView {
    fun initialCards(participants: Participants) {
        val playerNames = participants.players.joinToString(", ") { it.name }
        println("딜러와 ${playerNames}에게 2장의 나누었습니다.")
        participants.participants.forEach {
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
        participants.participants.forEach {
            println("${it.name}카드: ${it.cards.cards.joinToString(", ")} - 결과: ${it.cards.calculateScore()}")
        }
    }

    fun finalResult(finalResult: FinalResult) {
        println("## 최종 승패")
        if (finalResult.dealerResult.isBust) {
            println("딜러: 패")
            finalResult.playersResults.forEach { (player, _) ->
                println("${player.name}: 승")
            }
            return
        }
        println("딜러: ${finalResult.dealerResult.win}승 ${finalResult.dealerResult.lose}패")

        finalResult.playersResults.forEach { (player, gameResult) ->
            println("${player.name}: ${gameResult.result}")
        }
    }
}
