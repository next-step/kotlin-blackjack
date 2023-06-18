package blackjack.domain.view

import blackjack.domain.game.BlackjackGame
import blackjack.domain.player.Player
import blackjack.domain.player.Players

object ResultView {

    private const val START_BLACK_JACK_GAME_MESSAGE = "\n%s에게 %s장의 나누었습니다."
    private const val PLAYER_CARDS_MESSAGE = "%s 카드: %s"
    private const val GAME_RESULT_MESSAGE = "%s 카드: %s - 결과: %s"

    private const val SEPARATOR = ", "

    fun printStartBlackJackGame(players: Players) {
        val playerNames = players.joinToString(separator = SEPARATOR) { it.getName() }

        println(
            message = START_BLACK_JACK_GAME_MESSAGE.format(
                playerNames,
                BlackjackGame.INIT_HAND_COUNT,
            ),
        )

        players.forEach { printPlayerCards(player = it) }
    }

    fun printPlayerCards(player: Player) = println(
        message = PLAYER_CARDS_MESSAGE.format(
            player.getName(),
            convertCardView(player = player),
        ),
    )

    fun printGameResults(players: Players) {
        println()
        players.forEach { printGameResult(player = it) }
    }

    private fun printGameResult(player: Player) = println(
        message = GAME_RESULT_MESSAGE.format(
            player.getName(),
            convertCardView(player = player),
            1,
        ),
    )

    private fun convertCardView(player: Player) = Unit
}
