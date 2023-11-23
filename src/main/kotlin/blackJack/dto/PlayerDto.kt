package blackJack.dto

import blackJack.domain.Player

data class PlayerDto(val name: String, val cardsDto: CardsDto, val totalScore: Int = 0) {
    constructor(player: Player) : this(name = player.name, cardsDto = CardsDto(player.cards))
    constructor(player: Player , totalScore: Int) : this(name = player.name, cardsDto = CardsDto(player.cards), totalScore = totalScore)
}
