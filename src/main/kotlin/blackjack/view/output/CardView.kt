package blackjack.view.output

import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit

object CardView {
    fun of(card: Card) =
        extractRank(card.rank) + extractSuit(card.suit)

    private fun extractSuit(suit: Suit) = when (suit) {
        Suit.CLUB -> "클로버"
        Suit.DIAMOND -> "다이아"
        Suit.HEART -> "하트"
        Suit.SPADE -> "스페이드"
    }

    private fun extractRank(rank: Rank): String = when (rank) {
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
