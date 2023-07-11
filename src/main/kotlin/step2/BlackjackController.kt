package step2

import step2.domain.Player
import step2.domain.Players
import step2.ui.InputView
import step2.ui.OutputView

object BlackjackController {

    private const val DRAW_CARD_COUNT = 1
    private const val INITIAL_DRAW_CARD_COUNT = 2
    private const val ACCEPT_MORE_CARD_INPUT = "y"

    fun execute() {
        val players = initPlayers()
        play(players)
        result(players)
    }

    private fun initPlayers(): Players {
        val players = InputView.readPlayer()

        players.players.forEach {
            it.draw(INITIAL_DRAW_CARD_COUNT)
        }

        OutputView.printDrawInitialCards(players)

        return players
    }

    private fun play(players: Players) {
        players.players.forEach { player ->
            while (moreCard(player)) {
                player.draw(DRAW_CARD_COUNT)
                OutputView.printPlayersCards(player)
            }
        }
    }

    private fun result(players: Players) {
        players.players.forEach { player ->
            player.getResultScore()
            OutputView.printResult(player)
        }
    }

    private fun moreCard(player: Player): Boolean {
        val moreCard = if (!player.isBust()) InputView.askDrawMoreCard(player) else null
        return moreCard?.let { it == ACCEPT_MORE_CARD_INPUT } ?: false
    }
}
