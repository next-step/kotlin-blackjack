package blakjack.controller

import blakjack.domain.Dealer
import blakjack.domain.Player
import blakjack.view.InputView
import blakjack.view.OutputView

class BlackJackController {

    fun start() {
        val dealer = Dealer()
        val playerNames = InputView.readPlayerNames()
        val players = playerNames
            .map { Player(name = it) }
            .also { players -> dealer.initialDraw(players) }

        OutputView.printInitialPlayerCards(players)
        players.forEach { player -> dealer.hitOrStand(player) }
        OutputView.printPlayerCardsWithScore(players)
    }

    private fun Dealer.initialDraw(players: List<Player>) {
        players.forEach { player -> player.add(drawTwoCards()) }
    }

    private fun Dealer.hitOrStand(player: Player) {
        while (player.isUnderBlackjackScore && InputView.readHitOrStand(player.name)) {
            player.add(this.drawOneCard())
            OutputView.printPlayerCards(player)
        }
    }
}
