package blackjack.dto

import blackjack.domain.Player

class PlayerResponse(private val players: List<Player>) {
    fun toFormattedStringPlayerNames(): String {
        return players.joinToString(", ") { player -> player.getName() }
    }

    fun toFormattedStringPlayerCards(): String {
        return players.joinToString("\n") { player ->
            "${player.getName()}: ${player.displayHand()}"
        }
    }

    fun toFormattedStringPlayerResults(): String {
        return players.joinToString("\n") { player ->
            val total = player.calculateTotal()
            "${player.getName()}: ${player.displayHand()} - $total"
        }
    }
}
