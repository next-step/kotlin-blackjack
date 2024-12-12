package blackjack.step2.view

import blackjack.step2.domain.Participant

object ConsoleInputHandler {
    fun inputPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",").map { it.trim() }.filter { it.isNotBlank() }
    }

    fun askForMoreCard(participant: Participant): Boolean {
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

    fun notifyDealerDraw() {
        println("딜러는 카드를 더 받습니다.")
    }

    fun printPlayerCards(participant: Participant) {
        val formattedCards =
            participant.cards.all.joinToString(", ") {
                "${it.number.denomination}${it.type.value}"
            }
        println("${participant.name} 카드: $formattedCards")
    }
}
