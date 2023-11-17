package blackjack.ui

import blackjack.entity.Participant
import blackjack.entity.Participants

object OutputView {
    fun printEnterParticipantsName() {
        println(ENTER_PARTICIPANTS_NAME)
    }

    fun printParticipantsName(names: String) {
        println()
        val divideCardString = PRINT_DIVIDE_CARD_TO_PARTICIPANTS.format(names)
        println(divideCardString)
    }

    fun printParticipantsCard(participantList: Participants) {
        participantList.forEach { participant ->
            val participantInformation = createParticipantInformation(participant)
            println(participantInformation)
        }
        println()
    }

    private fun createParticipantInformation(participant: Participant): String {
        return PRINT_PARTICIPANTS_INFORMATION.format(
            participant.name,
            participant.cards.print()
        )
    }

    fun printGetOneMoreCard(participantName: String) {
        println(ASK_GET_MORE_ONE_CARD.format(participantName))
    }

    fun printNewCards(participant: Participant) {
        println(PRINT_PARTICIPANTS_INFORMATION.format(participant.name, participant.cards.print()))
    }

    fun printResult(result: List<String>) {
        println()
        result.forEach { println(it) }
    }

    fun winBlackJack(name: String) {
        println(WIN_BLACK_JACK.format(name))
    }

    private const val ENTER_PARTICIPANTS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val PRINT_DIVIDE_CARD_TO_PARTICIPANTS = "%s 에게 각각 2장의 카드를 나누었습니다."
    private const val PRINT_PARTICIPANTS_INFORMATION = "%s카드: %s"
    private const val ASK_GET_MORE_ONE_CARD = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val WIN_BLACK_JACK = "%s: 블랙잭 당첨!!!!!!!!!!!!"
}
