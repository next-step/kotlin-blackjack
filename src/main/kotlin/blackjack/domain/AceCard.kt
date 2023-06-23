package blackjack.domain

import blackjack.domain.Game.Companion.THRESHOLD
import kotlin.math.abs

data class AceCard(override val symbol: SymbolType) : Card {
    override val priority: Int = 3

    override fun name(): String = ACE_NAME

    override fun score(acc: Int): Int {
        val plusOne = MIN_VALUE + acc
        val plusEleven = MAX_VALUE + acc

        return when {
            plusEleven > THRESHOLD -> MIN_VALUE
            abs(THRESHOLD - plusOne) < abs(THRESHOLD - plusEleven) -> MIN_VALUE
            else -> MAX_VALUE
        }
    }

    companion object {
        const val ACE_NAME = "A"
        const val MIN_VALUE = 1
        const val MAX_VALUE = 11
    }
}
