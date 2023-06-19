package blackjack.domain.view

import blackjack.domain.view.model.view.CardView
import blackjack.domain.view.model.view.MatchResultViews
import blackjack.domain.view.model.view.ParticipantView
import blackjack.domain.view.model.view.ParticipantViewResult
import blackjack.domain.view.model.view.ParticipantViewResults
import blackjack.domain.view.model.view.ParticipantViews

object ResultView {

    private const val START_BLACK_JACK_GAME_MESSAGE = "\n%s와 %s에게 %s장의 나누었습니다."
    private const val PARTICIPANT_CARDS_MESSAGE = "%s 카드: %s"
    private const val GAME_RESULT_MESSAGE = "%s 카드: %s - 결과: %s"

    private const val HIT_DEALER_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n"
    private const val MATCH_RESULT_MESSAGE = "\n## 최종 승패"

    private const val PARTICIPANT_RESULT_MESSAGE = "%s: %s승 %s패"
    private const val SEPARATOR = ", "

    fun printStartBlackJackGame(players: ParticipantViews, dealer: ParticipantView, initHandCount: Int) {
        val playerNames = players.joinToString { it.name }

        println(
            message = START_BLACK_JACK_GAME_MESSAGE.format(dealer.name, playerNames, initHandCount),
        )

        printParticipantCards(participantView = dealer)
        players.forEach { printParticipantCards(participantView = it) }
    }

    fun printParticipantCards(participantView: ParticipantView) = println(
        message = PARTICIPANT_CARDS_MESSAGE.format(
            participantView.name,
            convertCardView(cards = participantView.cards),
        ),
    )

    fun printPlayResults(participantViewResults: ParticipantViewResults) {
        participantViewResults.forEach { printGameResult(playerViewResult = it) }
    }

    private fun printGameResult(playerViewResult: ParticipantViewResult) = println(
        message = GAME_RESULT_MESSAGE.format(
            playerViewResult.name,
            convertCardView(cards = playerViewResult.cards),
            playerViewResult.score,
        ),
    )

    private fun convertCardView(cards: List<CardView>) = cards.joinToString(separator = SEPARATOR) { card ->
        "${card.denomination}${card.suit}"
    }

    fun printDealerHit() = println(message = HIT_DEALER_MESSAGE)

    fun printMatchResults(matchResultViews: MatchResultViews) {
        println(message = MATCH_RESULT_MESSAGE)

        matchResultViews.forEach {
            println(
                message = PARTICIPANT_RESULT_MESSAGE.format(
                    it.participantName,
                    it.winScore,
                    it.loseScore
                ),
            )
        }
    }
}
