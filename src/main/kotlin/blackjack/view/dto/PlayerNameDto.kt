package blackjack.view.dto

import blackjack.domain.player.Player

data class PlayerNameDto(
    val name: String,
) {
    companion object {
        fun from(player: Player): PlayerNameDto = PlayerNameDto(
            player.name.value
        )
    }
}
