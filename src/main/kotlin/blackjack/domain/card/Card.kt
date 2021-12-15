package blackjack.domain.card

import blackjack.domain.Score

data class Card(val suitType: SuitType, val denomination: Denomination) {

    val score: Score = denomination.score

    fun isAce(): Boolean = denomination == Denomination.ACE
}
