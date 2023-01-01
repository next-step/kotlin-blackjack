package blackjack.view

import blackjack.domain.participant.state.role.Dealer
import blackjack.dto.ParticipantDto
import blackjack.dto.ResultsDto

object ResultView {
    private const val GAME_START_MESSAGE_PREFIX = "딜러와 "
    private const val GAME_START_MESSAGE_SUFFIX = "에게 2장의 나누었습니다."
    private const val CARD = "카드: "
    private const val COLON = ": "
    private const val GAME_RESULT_MESSAGE = " - 결과: "
    private const val DEALER_NAME = "딜러"
    private const val DEALER_DRAW_MESSAGE = "딜러는 ${Dealer.STOP_SCORE}이하라 한장의 카드를 더 받았습니다."
    private const val GAME_RESULT_TITLE = "## 최종 승패"
    private const val LINE_FEED = "\r\n"
    private const val SPACE = " "
    private const val FIRST_INDEX = 0

    fun printGameStartMessage(names: List<String>) {
        println(LINE_FEED + GAME_START_MESSAGE_PREFIX + names.joinToString() + GAME_START_MESSAGE_SUFFIX)
    }

    fun printParticipantCards(participant: ParticipantDto) {
        println(getNameAndCards(participant, true))
    }

    fun printResultWithScore(participant: ParticipantDto) {
        println(getNameAndCards(participant, false) + GAME_RESULT_MESSAGE + participant.score)
    }

    fun printDealerDrawMessage() {
        println(LINE_FEED + DEALER_DRAW_MESSAGE)
    }

    fun printLineFeed() {
        println()
    }

    fun printResult(results: ResultsDto) {
        println(GAME_RESULT_TITLE)
        val dealerResult = generateDealerResult(results)
        val playersResult = generatePlayersResult(results)
        println(dealerResult + LINE_FEED + playersResult)
    }

    private fun getNameAndCards(participant: ParticipantDto, hiddenDealerCards: Boolean): String {
        if (participant.name == DEALER_NAME) {
            return generateDealerCards(hiddenDealerCards, participant)
        }
        return participant.name + CARD + participant.cards.joinToString()
    }

    private fun generateDealerCards(hiddenDealerCards: Boolean, participant: ParticipantDto): String {
        if (hiddenDealerCards) {
            return participant.name + COLON + participant.cards[FIRST_INDEX]
        }
        return participant.name + SPACE + CARD + participant.cards.joinToString()
    }

    private fun generatePlayersResult(results: ResultsDto): String {
        return results.values.joinToString(LINE_FEED) { it.name + COLON + it.result }
    }

    private fun generateDealerResult(results: ResultsDto): String {
        var winCount = 0
        var loseCount = 0
        results.values.forEach {
            if (it.result == "승") {
                loseCount++
            }
            if (it.result == "패") {
                winCount++
            }
        }
        return DEALER_NAME + COLON + winCount + "승 " + loseCount + "패"
    }
}
