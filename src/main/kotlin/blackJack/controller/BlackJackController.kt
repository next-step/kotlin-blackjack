package blackJack.controller

import blackJack.model.Dealer
import blackJack.model.Player
import blackJack.view.InputView
import blackJack.view.OutputView

class BlackJackController {
    fun play() {
        val req = InputView.getNames()

        val candidates = req.map { Player(it) }
        val dealer = Dealer("dealer")

        val players = dealer.startGame(candidates)
        OutputView.printPlayersState(players)

        players.forEach { player ->
            shouldContinue(player, dealer)
        }

        OutputView.printFinalState(players)
    }

    private fun shouldContinue(player: Player, dealer: Dealer) {
        while (true) {
            val req = InputView.getPlayerInput(player.name)
            if (req == "n") {
                break
            }
            player.askMoreCard(dealer)

            if (dealer.isDrawCardAllowedFor(player).not()) {
                break
            }
            OutputView.printPlayerState(player)
        }
    }
}
