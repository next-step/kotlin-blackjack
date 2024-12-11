package blackjack.domain

import blackjack.domain.Card.Companion.ACE_VALUE_ONE
import blackjack.domain.Card.Companion.MAX_SUM

data class Cards(val value: List<Card>) {
    fun sumValues(): Int {
        var sum = 0
        value.forEach { card ->
            sum +=
                if (card.rank == Rank.ACE) {
                    if (sum + Rank.ACE.value > MAX_SUM) ACE_VALUE_ONE else Rank.ACE.value
                } else {
                    card.rank.value
                }
        }
        return sum
    }
}