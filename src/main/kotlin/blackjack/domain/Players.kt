package blackjack.domain

import blackjack.ui.model.PlayerDto

class Players(
    private val values: List<Player>
) : List<Player> by values {
    private val _values = values.toMutableList()

    fun giveToAllPlayers(cardPack: CardPack) {
        _values.forEach { it.takeCard(cardPack.pickCard()) }
    }

    fun toPlayerDtos(): List<PlayerDto> {
        return _values.map { it.toPlayerDto() }
    }

    fun addDealerAsPlayer(dealer: Dealer) {
        _values.add(dealer)
    }
}
