package dto

import domain.player.Player

data class PlayerDto(val name: String, val cards: CardsDto) {

    fun score() = cards.score

    companion object {
        fun from(player: Player) = PlayerDto(player.name(), CardsDto.from(player.cards()))
    }
}
