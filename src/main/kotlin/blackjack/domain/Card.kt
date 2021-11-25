package blackjack.domain

data class Card(val suitType: SuitType, val numberType: NumberType) {

    fun hasAce(): Boolean = numberType == NumberType.ACE
}
