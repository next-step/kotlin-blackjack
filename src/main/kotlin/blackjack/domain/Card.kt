package blackjack.domain

import blackjack.enums.Denomination
import blackjack.enums.Suit

class Card(val denomination: Denomination, val suit: Suit) {
    val value: Int = denomination.value
    val denominationName: String = denomination.printName
    val suitName: String = suit.suitName

    fun isAce(): Boolean {
        return denomination == Denomination.ACE
    }
}
