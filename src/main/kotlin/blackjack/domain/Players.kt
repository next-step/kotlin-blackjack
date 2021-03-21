package blackjack.domain

import blackjack.ui.model.PlayerDto

class Players(
    private val values: List<Player>
) : List<Player> by values {
    fun giveToAllPlayers(cardPack: CardPack) {
        values.forEach { it.takeCard(cardPack.pickCard()) }
    }

    fun toPlayerDtos(): List<PlayerDto> {
        return values.map { it.toPlayerDTO() }
    }

}
