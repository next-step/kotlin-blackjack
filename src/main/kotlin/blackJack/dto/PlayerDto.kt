package blackJack.dto

import blackJack.domain.Player

data class PlayerDto(val name: String, val cardsDto: CardsDto) {
    constructor(player: Player) : this(name = player.name, cardsDto = CardsDto(player.cards))
}
