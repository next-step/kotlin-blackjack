package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Suit

class BlackJackDealerResult(val dealer: Dealer) {

    val firstCard: String
        get() = "${dealer.cardSet.first().rank.rankName}${dealer.cardSet.first().suit.suitName()}"
    val cards: String
        get() = dealer.cardSet.joinToString(", ") { card ->
            "${card.rank.rankName}${card.suit.suitName()}"
        }
    val score: Int get() = dealer.cards.score().score

    private fun Suit.suitName(): String {
        return when (this) {
            Suit.SPADE -> "스페이드"
            Suit.HEART -> "하트"
            Suit.DIAMOND -> "다이아몬드"
            Suit.CLUB -> "클로버"
        }
    }
}
