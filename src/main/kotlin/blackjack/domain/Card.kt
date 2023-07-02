package blackjack.domain

import blackjack.enums.Denomination
import blackjack.enums.Suit

class Card(val denomination: Denomination, val suit: Suit) {
    val value: Int
        get() {
            return denomination.value
        }

    val name: String
        get() {
            return "${denomination.printName}${suit.suitName}"
        }

    fun isAce(): Boolean {
        return denomination == Denomination.ACE
    }
}
