package blackjack.dto

import blackjack.domain.Player

class SinglePlayerResponse(private val player: Player) {
    fun toFormattedStringPlayerName(): String {
        return player.getName()
    }

    fun toFormattedStringPlayerCards(): String {
        return player.displayHand()
    }
}
