package blackjack.service

import blackjack.model.Player

class PlayerInitializer {
    companion object {
        fun fromString(input: String): List<Player> {
            return input.split(",").map(::Player)
        }
    }
}
