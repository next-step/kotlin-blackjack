package blackjack.domain

import blackjack.ui.model.PlayerCardResult
import blackjack.ui.model.PlayerDto

class Players(
    values: List<Player>
) : List<Player> by values {
    private val _values = values.toMutableList()

    fun giveToAllPlayers(cardPack: CardPack) {
        _values.forEach { it.takeCard(cardPack.pickCard()) }
    }

    fun toPlayerDtos(): List<PlayerDto> {
        return _values.map { it.toPlayerDto() }
    }

    fun toPlayerCardResults(): List<PlayerCardResult> {
        return _values.map { PlayerCardResult(it) }
    }

    fun addDealerAsPlayer(dealer: Dealer) {
        _values.add(DEALER_INDEX, dealer)
    }

    companion object {
        private const val DEALER_INDEX = 0
    }
}
