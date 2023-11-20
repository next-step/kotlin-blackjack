package blackjack.business.util

data class Money(private val value: Int) {
    fun lose(): Money {
        return Money(-value)
    }
}
