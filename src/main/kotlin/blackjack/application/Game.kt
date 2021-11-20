package blackjack.application

import blackjack.domain.player.Player
import blackjack.views.OutputView

object Game {

    fun showPlayerResult(player: Player) {
        OutputView.printCards(player)
    }

    fun showDealerCardReceived() {
        OutputView.printDealerCardReceived()
    }
}
