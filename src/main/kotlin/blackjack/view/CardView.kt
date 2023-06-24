package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Denomination.ACE
import blackjack.domain.card.Denomination.EIGHT
import blackjack.domain.card.Denomination.FIVE
import blackjack.domain.card.Denomination.FOUR
import blackjack.domain.card.Denomination.JACK
import blackjack.domain.card.Denomination.KING
import blackjack.domain.card.Denomination.NINE
import blackjack.domain.card.Denomination.QUEEN
import blackjack.domain.card.Denomination.SEVEN
import blackjack.domain.card.Denomination.SIX
import blackjack.domain.card.Denomination.TEN
import blackjack.domain.card.Denomination.THREE
import blackjack.domain.card.Denomination.TWO
import blackjack.domain.card.Suit
import blackjack.domain.card.Suit.CLOVER
import blackjack.domain.card.Suit.DIAMOND
import blackjack.domain.card.Suit.HEART
import blackjack.domain.card.Suit.SPADE

fun parseCardView(card: Card): String =
    "${DenominationView.from(card.denomination).view}${SuitView.from(card.suit).view}"

enum class DenominationView(
    val denomination: Denomination,
    val view: String,
) {
    ACE_VIEW(ACE, "A"),
    TWO_VIEW(TWO, "2"),
    THREE_VIEW(THREE, "3"),
    FOUR_VIEW(FOUR, "4"),
    FIVE_VIEW(FIVE, "5"),
    SIX_VIEW(SIX, "6"),
    SEVEN_VIEW(SEVEN, "7"),
    EIGHT_VIEW(EIGHT, "8"),
    NINE_VIEW(NINE, "9"),
    TEN_VIEW(TEN, "10"),
    JACK_VIEW(JACK, "J"),
    QUEEN_VIEW(QUEEN, "Q"),
    KING_VIEW(KING, "K"),
    ;

    companion object {
        fun from(denomination: Denomination) = values().firstOrNull { it.denomination == denomination }
            ?: throw IllegalArgumentException("뷰에서 지원하지 않는 끗수다.")
    }
}

enum class SuitView(
    val suit: Suit,
    val view: String,
) {
    SPADE_VIEW(SPADE, "스페이드"),
    CLOVER_VIEW(CLOVER, "클로버"),
    DIAMOND_VIEW(DIAMOND, "다이아몬드"),
    HEART_VIEW(HEART, "하트"),
    ;

    companion object {
        fun from(suit: Suit) = values().firstOrNull { it.suit == suit }
            ?: throw IllegalArgumentException("뷰에서 지원하지 않는 문양이다.")
    }
}
