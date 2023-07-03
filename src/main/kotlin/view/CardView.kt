package view

import card.Card
import card.Denomination
import card.Denomination.ACE
import card.Denomination.EIGHT
import card.Denomination.FIVE
import card.Denomination.FOUR
import card.Denomination.JACK
import card.Denomination.KING
import card.Denomination.NINE
import card.Denomination.QUEEN
import card.Denomination.SEVEN
import card.Denomination.SIX
import card.Denomination.TEN
import card.Denomination.THREE
import card.Denomination.TWO
import card.Suit

fun parseCard(card: Card): String {
    val suitView = SuitView.viewOf(card.suit)
    val denominationView = DenominationView.viewOf(card.denominations)
    return "$denominationView$suitView"
}

private enum class DenominationView(
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
    KING_VIEW(KING, "K"), ;

    companion object {
        fun viewOf(denomination: Denomination): String =
            values().firstOrNull { it.denomination == denomination }?.view
                ?: throw IllegalArgumentException("지원하지 않는 숫자입니다.")
    }
}

private enum class SuitView(
    val suit: Suit,
    val view: String,
) {
    CLOVER_VIEW(Suit.CLOVER, "클로버"),
    SPADE_VIEW(Suit.SPADE, "스페이드"),
    HEART_VIEW(Suit.HEART, "하트"),
    DIAMOND_VIEW(Suit.DIAMOND, "다이아몬드"), ;

    companion object {
        fun viewOf(suit: Suit): String =
            values().firstOrNull { it.suit == suit }?.view ?: throw IllegalArgumentException("지원하지 못하는 문양입니다.")
    }
}
