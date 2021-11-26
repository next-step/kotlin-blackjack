package blackjack.strategy.split

object SingleCommaSplitStrategy : SplitStrategy {
    private const val SINGLE_COMMA = ","
    override fun split(target: String): List<String> = target.split(SINGLE_COMMA)
}
