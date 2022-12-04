package blackjack.util

object RandomNumberGenerator {
    fun generate(range: IntRange): Int = range.random()
}
