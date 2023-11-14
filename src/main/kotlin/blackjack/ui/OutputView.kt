package blackjack.ui

import blackjack.entity.Card
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
            participant.cards.joinToString { card ->
                val number = compositeNumberFromNumber(card)
                "${number}${card.shape.shapeName}"
            })
    }

    private fun compositeNumberFromNumber(card: Card) =
        card.number.number.takeIf { it in MINIMUM_NUMBER_NOT_CHARACTER..MAXIMUM_NUMBER_NOT_CHARACTER }
            ?: card.number.name

    private const val ENTER_PARTICIPANTS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val PRINT_DIVIDE_CARD_TO_PARTICIPANTS = "%s 에게 각각 2장의 카드를 나누었습니다."
    private const val PRINT_PARTICIPANTS_INFORMATION = "%s카드: %s"
    private const val MINIMUM_NUMBER_NOT_CHARACTER = 2
    private const val MAXIMUM_NUMBER_NOT_CHARACTER = 9
}
