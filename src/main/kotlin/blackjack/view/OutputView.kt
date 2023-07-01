package blackjack.view

import blackjack.Player

interface OutputView {
    fun showInitialStatus(players: List<Player>)
    fun showCurrentStatusOf(player: Player)
}
