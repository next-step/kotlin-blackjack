package blackjack.view

import blackjack.domain.DEALER
import blackjack.domain.Participant
import blackjack.exception.InvalidInputValueException

object InputView {

    fun createParticipants(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val participants = readln()
        return listOf(DEALER) + participants.split(",")
    }

    fun needMoreCard(player: Participant): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readln().toBoolean()
    }

    private fun String.toBoolean(): Boolean {
        return when (uppercase()) {
            YES -> true
            NO -> false
            else -> throw InvalidInputValueException()
        }
    }

    private const val YES = "Y"
    private const val NO = "N"
}
