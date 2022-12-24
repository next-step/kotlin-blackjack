package blackjack.domain

import blackjack.domain.Number.ACE
import blackjack.domain.Number.JACK
import blackjack.domain.Number.KING
import blackjack.domain.Number.QUEEN

data class Card(
    val number: Number,
    val sharp: Sharp
) {

    fun sumAllScore() = number.values.sum()
    fun isAce() = this.number == ACE

    fun isTenNumberCard() = listOf(JACK, QUEEN, KING).contains(number)
}
