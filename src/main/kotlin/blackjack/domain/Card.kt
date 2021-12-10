package blackjack.domain

data class Card(val suitType: SuitType, val denomination: Denomination) {

    val score: Score = denomination.score

    fun isAce(): Boolean = denomination == Denomination.ACE
}
