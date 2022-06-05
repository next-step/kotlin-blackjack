package blackjack.domain

data class Card(val suite: Suite, val denomination: Denomination) {

    val isAce: Boolean
        get() = denomination == Denomination.ACE
}
