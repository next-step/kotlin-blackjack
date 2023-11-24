package blackJack.dto

import blackJack.domain.player.Dealer
import blackJack.domain.player.Player

data class DealerDto(val name: String, val cardsDto: CardsDto, val status: String) {
    constructor(dealer: Dealer) : this(
        name = dealer.name,
        cardsDto = CardsDto(dealer.cards),
        status = dealer.status.toString()
    )
    constructor(player: Player, totalScore: Int) : this(name = player.name, cardsDto = CardsDto(player.cards), totalScore = totalScore)

}
