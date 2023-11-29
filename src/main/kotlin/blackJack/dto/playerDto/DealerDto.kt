package blackJack.dto.playerDto

import blackJack.domain.player.Dealer

data class DealerDto(val name: String, val cardsDto: CardsDto, val totalScore: Int = 0) {
    constructor(dealer: Dealer) : this(name = dealer.name, cardsDto = CardsDto(dealer.cards))
    constructor(dealer: Dealer, totalScore: Int) : this(name = dealer.name, cardsDto = CardsDto(dealer.cards), totalScore = totalScore)
}
