package dto

import domain.card.Denomination
import domain.card.PlayingCard
import domain.card.Suit

class CardDto(card: PlayingCard) {
    private val cardName = toString(card.denomination) + toString(card.suit)
    override fun toString() = cardName

    private fun toString(denomination: Denomination) = when (denomination) {
        Denomination.ACE -> "A"
        Denomination.KING -> "K"
        Denomination.QUEEN -> "Q"
        Denomination.JACK -> "J"
        else -> denomination.score.toString()
    }

    private fun toString(suit: Suit) = when (suit) {
        Suit.CLUBS -> "클로버"
        Suit.DIAMONDS -> "다이아몬드"
        Suit.HEARTS -> "하트"
        Suit.SPADES -> "스페이드"
    }
}
