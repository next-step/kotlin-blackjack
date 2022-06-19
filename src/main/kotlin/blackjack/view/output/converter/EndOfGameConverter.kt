package blackjack.view.output.converter

import blackjack.domain.BlackjackGameResult

object EndOfGameConverter : OutputConverter<BlackjackGameResult> {
    override fun convert(printable: BlackjackGameResult): String {
        return "${printable.toCardsText()}\n\n## 최종 승패\n${printable.toMatchStatusText()}"
    }

    private fun BlackjackGameResult.toCardsText(): String {
        return value.joinToString("\n") { result ->
            val participant = result.participant
            "${PlayerConverter.convert(participant)} - 결과: ${participant.score.value}"
        }
    }

    private fun BlackjackGameResult.toMatchStatusText(): String {
        return value.joinToString("\n") { result ->
            "${result.participant.name.value}: ${MatchStatusConverter.convert(result.matchStatus)}"
        }
    }
}
