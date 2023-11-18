package blackJack.dto

import blackJack.domain.Card

data class CardDto(val suit: String, val rank: Int) {
    constructor(card: Card) : this(suit = card.suit.toString(), rank = card.rank.score)
}
