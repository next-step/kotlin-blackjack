package dto

import domain.player.Player

open class PlayerDto(player: Player) {
    val name = player.name()
    private val cardsDto = CardsDto(player.cards())
    val cards = cardsDto.cards
    val score = cardsDto.score
}
