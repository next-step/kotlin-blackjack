package blackjack.domain

data class Card(val suitType: SuitType, val denomination: Denomination) {

    fun hasAce(): Boolean = denomination == Denomination.ACE
}
