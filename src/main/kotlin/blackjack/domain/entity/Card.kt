package blackjack.domain.entity

import blackjack.domain.entity.enums.Denomination
import blackjack.domain.entity.enums.Suit

data class Card(val suit: Suit, val denomination: Denomination) {
    override fun toString(): String {
        val cardNumber = if (denomination == Denomination.ACE) ACE_STRING else denomination.cardNumber
        return "$cardNumber ${suit.name}"
    }
    companion object {
        private const val ACE_STRING = "A"
    }
}
