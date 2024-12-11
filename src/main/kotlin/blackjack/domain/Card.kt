package blackjack.domain

import blackjack.domain.Card.Companion.ACE_VALUE_ONE
import blackjack.domain.Card.Companion.MAX_SUM

data class Card(val rank: Rank, val suit: Suit) {
    private fun isAce() = rank == Rank.ACE

    fun isOverMaxSum(currentCardSum: Int): Boolean {
        return if (isAce()) {
            currentCardSum + ACE_VALUE_ONE > MAX_SUM && currentCardSum + this.rank.value > MAX_SUM
        } else {
            currentCardSum + this.rank.value > MAX_SUM
        }
    }

    companion object {
        const val ACE_VALUE_ONE = 1
        const val MAX_SUM = 21
    }
}

fun List<Card>.sumValues(): Int {
    var sum = 0
    this.forEach { card ->
        sum +=
            if (card.rank == Rank.ACE) {
                if (sum + Rank.ACE.value > MAX_SUM) ACE_VALUE_ONE else Rank.ACE.value
            } else {
                card.rank.value
            }
    }
    return sum
}
