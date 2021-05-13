package blackjack.view

import blackjack.domain.CardNumber
import blackjack.domain.CardNumber.ACE
import blackjack.domain.CardNumber.EIGHT
import blackjack.domain.CardNumber.FIVE
import blackjack.domain.CardNumber.FOUR
import blackjack.domain.CardNumber.JACK
import blackjack.domain.CardNumber.KING
import blackjack.domain.CardNumber.NINE
import blackjack.domain.CardNumber.QUEEN
import blackjack.domain.CardNumber.SEVEN
import blackjack.domain.CardNumber.SIX
import blackjack.domain.CardNumber.TEN
import blackjack.domain.CardNumber.THREE
import blackjack.domain.CardNumber.TWO

enum class CardDisplayNumber(val value: CardNumber, val displayName: String) {
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
    ACE_VIEW(ACE, "A");

    companion object {
        fun retrieveDisplayName(value: CardNumber): String {
            return values().first { it.value == value }.displayName
        }
    }
}
