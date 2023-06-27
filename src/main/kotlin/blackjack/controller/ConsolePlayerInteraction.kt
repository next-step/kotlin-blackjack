package blackjack.controller

import blackjack.domain.Player
import blackjack.domain.PlayerInteraction

class ConsolePlayerInteraction : PlayerInteraction {

    override fun showInitDraw(initCardCount: Int, players: List<Player>) {
        ResultView.writeGameInit(initCardCount, players)
    }

    override fun askHit(player: Player): Boolean {
        return InputView.askPlayerHit(player)
    }

    override fun showPlayer(player: Player) {
        ResultView.writePlayer(player)
    }
}
