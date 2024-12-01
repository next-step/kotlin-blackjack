package blackjack.application

import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.presentation.InputView
import blackjack.presentation.OutputView

object BlackjackApplication {
    private val inputView: InputView = InputView()
    private val outputView: OutputView = OutputView()
    private val deck: Deck = Deck()

    fun start() {
        val names = inputView.inputNames()
        val players = names.map(::Player)

        initialDeal(players)
        players.forEach(::progress)
        outputView.printResult(players)
    }

    private fun initialDeal(players: List<Player>) {
        players.forEach { player ->
            repeat(2) {
                hit(player)
            }
        }
        outputView.printInitialCards(players)
    }

    private fun progress(player: Player) {
        if (player.isPlayable() && inputView.inputHitOrStay(player.name)) {
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
