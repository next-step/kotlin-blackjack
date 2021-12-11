package blackjack.entity

import blackjack.entity.enums.Denomination
import blackjack.entity.enums.Suit

data class Card(val suit: Suit, val denomination: Denomination) {
    override fun toString(): String {
        val cardNumber = if (denomination == Denomination.ACE) "A" else denomination.cardNumber
        return "$cardNumber ${suit.name}"
    }
}
