package dto

import domain.card.PlayingCards

data class CardsDto(val cards: List<CardDto>, val score: Int) : List<CardDto> by cards {
    companion object {
        fun from(playingCards: PlayingCards) = CardsDto(playingCards.map { CardDto.from(it) }, playingCards.score())
    }
}
