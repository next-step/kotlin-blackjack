package blackjack.view

import blackjack.business.participants.Player

fun interface PlayerOutputHandler {
    fun print(player: Player)
}
