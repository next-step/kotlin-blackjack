package blackjack.domain.view

import blackjack.domain.view.model.CardView
import blackjack.domain.view.model.PlayerView
import blackjack.domain.view.model.PlayerViewResult
import blackjack.domain.view.model.PlayerViewResults
import blackjack.domain.view.model.PlayerViews

object ResultView {

    private const val START_BLACK_JACK_GAME_MESSAGE = "\n%s에게 %s장의 나누었습니다."
    private const val PLAYER_CARDS_MESSAGE = "%s 카드: %s"
    private const val GAME_RESULT_MESSAGE = "%s 카드: %s - 결과: %s"

    private const val SEPARATOR = ", "

    fun printStartBlackJackGame(playerViews: PlayerViews, initHandCount: Int) {
        val playerNames = playerViews.joinToString { it.name }

        println(
            message = START_BLACK_JACK_GAME_MESSAGE.format(playerNames, initHandCount),
        )

        playerViews.forEach { printPlayerCards(playerView = it) }
    }

    fun printPlayerCards(playerView: PlayerView) = println(
        message = PLAYER_CARDS_MESSAGE.format(
            playerView.name,
            convertCardView(cards = playerView.cards),
        ),
    )

    fun printGameResults(playerViewResults: PlayerViewResults) {
        println()
        playerViewResults.forEach { printGameResult(playerViewResult = it) }
    }

    private fun printGameResult(playerViewResult: PlayerViewResult) = println(
        message = GAME_RESULT_MESSAGE.format(
            playerViewResult.name,
            convertCardView(cards = playerViewResult.cards),
            playerViewResult.score,
        ),
    )

    private fun convertCardView(cards: List<CardView>) = cards.joinToString(separator = SEPARATOR) { card ->
        "${card.denomination}${card.suit}"
    }
}
