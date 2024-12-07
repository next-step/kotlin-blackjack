package blackjack.application

import blackjack.domain.Deck
import blackjack.domain.INITIAL_DRAW_COUNT
import blackjack.domain.Player
import blackjack.presentation.InputView
import blackjack.presentation.OutputView

class BlackjackGame(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val deck: Deck = Deck(),
) {
    fun start() {
        val names = inputView.inputNames()
        val players = names.map(::Player)

        initialDraw(players)
        players.forEach(::progress)
        outputView.printResult(players)
    }

    private fun initialDraw(players: List<Player>) {
        players.forEach { player ->
            repeat(INITIAL_DRAW_COUNT) {
                hit(player)
            }
        }
        outputView.printInitialCards(players)
    }

    private fun progress(player: Player) {
        if (player.isPlayable && inputView.inputHitOrStay(player.name)) {
            hit(player)
            outputView.printPlayerCards(player)
            progress(player)
        } else {
            player.toStay()
        }
    }

    private fun hit(player: Player) {
        player.hit(deck)
    }
}
