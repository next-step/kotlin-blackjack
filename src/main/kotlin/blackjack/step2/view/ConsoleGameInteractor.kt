package blackjack.step2.view

import blackjack.step2.domain.GameInteractor
import blackjack.step2.domain.Participant

class ConsoleGameInteractor : GameInteractor {
    override fun askForMoreCard(participant: Participant): Boolean {
        println("${participant.name}는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)")
        return when (readln().lowercase()) {
            "y" -> true
            "n" -> false
            else -> {
                println("잘못된 입력입니다. 다시 입력해주세요.")
                askForMoreCard(participant)
            }
        }
    }

    override fun notifyDealerDraw() {
        println("딜러는 카드를 더 받습니다.")
    }

    override fun printPlayerCards(participant: Participant) {
        val formattedCards =
            participant.cards.all.joinToString(", ") {
                "${it.number.denomination}${it.type.value}"
            }
        println("${participant.name} 카드: $formattedCards")
    }
}
