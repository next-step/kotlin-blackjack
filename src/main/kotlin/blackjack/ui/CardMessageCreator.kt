package blackjack.ui

import blackjack.domain.Card
import blackjack.domain.Rank
import blackjack.domain.Suit

object CardMessageCreator {
    private const val SEPARATOR = ", "

    fun create(cards: List<Card>): String {
        return cards
            .joinToString(separator = SEPARATOR) { card -> convertToSignature(card) }
    }

    private fun convertToSignature(card: Card): String {
        val rankSignature = convertToSignature(card.rank)
        val suitSignature = convertToSignature(card.suit)
        return "${rankSignature}$suitSignature"
    }

    private fun convertToSignature(rank: Rank): String {
        return when (rank) {
            Rank.ACE -> "A"
            Rank.TWO -> "2"
            Rank.THREE -> "3"
            Rank.FOUR -> "4"
            Rank.FIVE -> "5"
            Rank.SIX -> "6"
            Rank.SEVEN -> "7"
            Rank.EIGHT -> "8"
            Rank.NINE -> "9"
            Rank.TEN -> "10"
            Rank.JACK -> "J"
            Rank.QUEEN -> "Q"
            Rank.KING -> "K"
        }
    }

    private fun convertToSignature(suit: Suit): String {
        return when (suit) {
            Suit.HEARTS -> "하트"
            Suit.SPADES -> "스페이드"
            Suit.DIAMONDS -> "다이아"
            Suit.CLUBS -> "클로버"
        }
    }
}
