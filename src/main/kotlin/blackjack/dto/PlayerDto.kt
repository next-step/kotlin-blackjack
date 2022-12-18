package blackjack.dto

import blackjack.domain.player.Player

data class PlayerDto(val name: String, val cards: String, val score: Int) {
    companion object {
        fun from(player: Player): PlayerDto {
            return PlayerDto(player.name.toString(), player.cards.toString(), player.getScore())
        }
    }
}
