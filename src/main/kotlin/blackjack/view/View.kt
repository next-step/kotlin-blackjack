package blackjack.view

import blackjack.vo.GameResultVO
import blackjack.vo.ParticipantScoreVO
import blackjack.vo.ParticipantVO

object InputView {
    private const val COMMA_SEPARATOR = ","

    fun readPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readlnOrNull() ?: throw IllegalArgumentException("이름을 입력해주세요.")
        return names.split(COMMA_SEPARATOR)
    }

    fun readDrawMore(playerName: String): Boolean {
        println("${playerName}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readlnOrNull()) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException("y 또는 n을 입력해주세요.")
        }
    }
}

object ResultView {
    private const val COMMA_SEPARATOR = ", "
    private const val NEW_LINE = "\n"

    fun printParticipant(participant: ParticipantVO) {
        println(participantText(participant))
    }

    fun printCardHands(participantVOs: List<ParticipantVO>) {
        val namesText = participantVOs.joinToString(COMMA_SEPARATOR) { it.name }
        val playersText = participantVOs.joinToString(NEW_LINE, transform = ResultView::participantText)
        println(
            """
            |${namesText}에게 2장의 카드를 나누었습니다
            |$playersText
            |""".trimMargin()
        )
    }

    fun printDealerHit() {
        println("${NEW_LINE}딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printParticipantScores(participantScoreVOs: List<ParticipantScoreVO>) {
        val participantScoresText = participantScoreVOs.joinToString(NEW_LINE) {
            "${participantText(it.participantVO)} - 결과:${it.score}"
        }
        println(participantScoresText)
    }

    fun printGameResult(gameResultVO: GameResultVO) {
        val playersGameResult = gameResultVO.playerGameResultVOs
            .joinToString(NEW_LINE) { "${it.name}: ${if (it.isWinner) "승" else "패"}" }

        println(
            """$NEW_LINE
            |## 최종 승패
            |딜러: ${gameResultVO.dealerWinCount}승 ${gameResultVO.dealerLoseCount}패
            |$playersGameResult
            |""".trimMargin()
        )
    }

    private fun participantText(participant: ParticipantVO): String {
        val cards = participant.cards.joinToString(COMMA_SEPARATOR) { "${it.denomination.symbol}${it.suit.name}" }
        return "${participant.name}카드: $cards"
    }
}
