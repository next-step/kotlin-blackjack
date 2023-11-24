package blackJack.dto

import blackJack.domain.card.Cards

data class CardsDto(val cardDtos: List<CardDto>) {
    constructor(cards: Cards) : this(cardDtos = cards.cards.map { CardDto(it) })
}
