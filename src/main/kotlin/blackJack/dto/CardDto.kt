package blackJack.dto

import blackJack.domain.card.Card

data class CardDto(val suit: String, val rank: String) {
    constructor(card: Card) : this(suit = card.suit.toString(), rank = card.rank.shape)
}
