package blackjack.domain

data class Card(val suitType: SuitType, val denomination: Denomination) {

    fun isAce(): Boolean = denomination == Denomination.ACE
}
