package blackjack.view

import blackjack.business.Player

fun interface PlayerOutputHandler {
    fun print(player: Player)
}
