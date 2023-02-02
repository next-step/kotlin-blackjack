package blackjack.view

import blackjack.dto.Output
import blackjack.dto.ParticipantDto
import blackjack.dto.ParticipantsDto
import blackjack.dto.ResultsDto

object ResultView : Output {
    private const val GAME_START_MESSAGE_PREFIX = "딜러와 "
    private const val GAME_START_MESSAGE_SUFFIX = "에게 2장의 나누었습니다."
    private const val CARD = "카드: "
    private const val COLON = ": "
    private const val GAME_RESULT_MESSAGE = " - 결과: "
    private const val DEALER_NAME = "딜러"
    private const val PLAYER_BUST = "는 버스트되었습니다."
    private const val PLAYER_BLACKJACK = "는 블랙잭입니다."
    private const val DEALER_DRAW_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val GAME_RESULT_TITLE = "## 최종 수익"
    private const val LINE_FEED = "\r\n"
    private const val SPACE = " "
    private const val FIRST_INDEX = 0

    override fun printGameStartMessage(names: List<String>) {
        println(LINE_FEED + GAME_START_MESSAGE_PREFIX + names.joinToString() + GAME_START_MESSAGE_SUFFIX)
    }

    override fun printParticipantCards(participant: ParticipantDto) {
        println(getNameAndCards(participant, true))
    }

    override fun printResultWithScore(participants: ParticipantsDto) {
        println()
        participants.participants.forEach { participant ->
            println(getNameAndCards(participant, false) + GAME_RESULT_MESSAGE + participant.score)
        }
    }

    override fun printResult(results: ResultsDto) {
        println(LINE_FEED + GAME_RESULT_TITLE)
        results.values.forEach {
            println(it.name + COLON + it.profit)
        }
    }

    override fun printPlayerBust(name: String) {
        println(name + PLAYER_BUST)
    }

    override fun printPlayerBlackjack(name: String) {
        println(name + PLAYER_BLACKJACK)
    }

    override fun printDealerDrawMessage() {
        println(LINE_FEED + DEALER_DRAW_MESSAGE)
    }

    override fun printParticipantsCards(participants: ParticipantsDto) {
        participants.participants.forEach {
            printParticipantCards(it)
        }
        println()
    }

    private fun getNameAndCards(participant: ParticipantDto, hiddenDealerCards: Boolean): String {
        if (participant.name == DEALER_NAME) {
            return generateDealerCards(participant, hiddenDealerCards)
        }
        return participant.name + CARD + participant.cards.joinToString()
    }

    private fun generateDealerCards(participant: ParticipantDto, hiddenDealerCards: Boolean): String {
        if (hiddenDealerCards) {
            return participant.name + COLON + participant.cards[FIRST_INDEX]
        }
        return participant.name + SPACE + CARD + participant.cards.joinToString()
    }
}
