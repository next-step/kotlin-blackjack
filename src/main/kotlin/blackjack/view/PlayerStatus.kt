package blackjack.view

import blackjack.domain.Player

data class PlayerStatus(
    val name: String,
    val handRepresent: String,
    val optimalValue: Int
) {
    companion object {
        fun of(player: Player): PlayerStatus {
            return PlayerStatus(player.name, player.showHands(), player.optimalValue())
        }
    }
}
