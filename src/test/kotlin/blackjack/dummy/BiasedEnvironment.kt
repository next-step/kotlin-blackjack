package blackjack.dummy

import blackjack.model.player.Player

open class BiasedEnvironment(private val potentialWinnerName: String) {
    fun Player.isPotentialWinner() = potentialWinnerName == this.name
}
