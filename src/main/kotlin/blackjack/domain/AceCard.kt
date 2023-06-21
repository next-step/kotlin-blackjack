package blackjack.domain

import kotlin.math.abs

data class AceCard(override val symbol: SymbolType) : Card {
    override val priority: Int = 3

    override fun name(): String = ACE_NAME

    override fun score(acc: Int): Int =
        if (abs(THRESHOLD - (MIN_VALUE + acc)) < abs(THRESHOLD - (MAX_VALUE + acc))) MIN_VALUE else MAX_VALUE

    companion object {
        const val ACE_NAME = "A"
        const val MIN_VALUE = 1
        const val MAX_VALUE = 11
        const val THRESHOLD = 21
    }
}
