package blackjack.controller

import blackjack.model.player.Dealer
import blackjack.model.player.Player
import blackjack.model.state.PlayerState
import blackjack.model.strategy.RandomStrategy
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {
    fun start() {
        val dealer = Dealer(RandomStrategy())
        val players = InputView.inputPlayers().map(::Player)
        players.forEach { player ->
            repeat(2) { dealer.receive(player) }
        }

        OutputView.printPlayerInitStatus(players)

        players.forEach { player ->
            while (InputView.inputPlayerChoice(player.name) == PlayerState.HIT) {
                dealer.receive(player)
                OutputView.printPlayerCards(player)
            }
        }
    }
}

fun main() {
    BlackjackController().start()
}
