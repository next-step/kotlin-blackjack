package blackjack.view.model

import blackjack.domain.player.Player

data class PlayerNameModel(
    val name: String,
) {
    companion object {
        fun from(player: Player): PlayerNameModel = PlayerNameModel(
            player.name.value
        )
    }
}
