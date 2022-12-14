package blackjack.service

import blackjack.model.Player

object PlayerInitializer {
    fun fromString(input: String): List<Player> {
        return input.split(",").map(::Player)
    }
}
