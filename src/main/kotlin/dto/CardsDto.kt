package dto

import domain.card.PlayingCards

class CardsDto(playingCards: PlayingCards) {
    val cards = playingCards.map { CardDto(it) }
    val score = playingCards.score()
}
