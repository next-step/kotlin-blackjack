package blackjack.view

import blackjack.dto.ParticipantDto

object ResultView {
    private const val GAME_START_MESSAGE_PREFIX = "딜러와 "
    private const val GAME_START_MESSAGE_SUFFIX = "에게 2장의 나누었습니다."
    private const val CARD = "카드: "
    private const val COLON = ": "
    private const val GAME_RESULT_MESSAGE = " - 결과: "
    private const val DEALER_NAME = "딜러"
    private const val LINE_FEED = "\r\n"
    private const val FIRST_INDEX = 0

    fun printGameStartMessage(names: List<String>) {
        println(LINE_FEED + GAME_START_MESSAGE_PREFIX + names.joinToString() + GAME_START_MESSAGE_SUFFIX)
    }

    fun printParticipantCards(participant: ParticipantDto) {
        println(getNameAndCards(participant))
    }

    fun printResultWithScore(participant: ParticipantDto) {
        println(getNameAndCards(participant) + GAME_RESULT_MESSAGE + participant.score)
    }

    fun printLineFeed() {
        println()
    }

    private fun getNameAndCards(participant: ParticipantDto): String {
        if (participant.name == DEALER_NAME) {
            return participant.name + COLON + participant.cards[FIRST_INDEX]
        }
        return participant.name + CARD + participant.cards.joinToString()
    }
}
