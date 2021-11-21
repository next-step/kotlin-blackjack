package blackjack.strategy.split

fun interface SplitStrategy {
    fun split(target: String): List<String>
}
