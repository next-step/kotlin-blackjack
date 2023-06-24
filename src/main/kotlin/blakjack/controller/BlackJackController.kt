package blakjack.controller

import blakjack.domain.Dealer
import blakjack.domain.Player
import blakjack.view.InputView
import blakjack.view.OutputView

class BlackJackController {
    private val dealer = Dealer()

    fun start() {
        val playerNames = InputView.readPlayerNames()
        val players = playerNames.map { Player(name = it) }.also { initialDraw(it) }

        OutputView.printInitialPlayerCards(players)
        players.forEach(::hitOrStand)
        OutputView.printPlayerCardsWithScore(players)
    }

    private fun initialDraw(players: List<Player>) {
        players.forEach { player -> player.add(dealer.drawTwoCards()) }
    }

    private fun hitOrStand(player: Player) {
        while (player.isUnderBlackjackScore && InputView.readHitOrStand(player.name)) {
            player.add(dealer.drawOneCard())
            OutputView.printPlayerCards(player)
        }
    }
}
