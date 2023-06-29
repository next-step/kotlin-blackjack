package blackjack.view

import blackjack.Player

interface InputView {
    fun getPlayers(): List<String>

    fun askCastCardToPlayer(player: Player): Boolean
}
