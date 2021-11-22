package blackjack.domain.game

private const val INITIAL_VALUE = 0
private const val MONEY_UNIT = 1000
private const val NO_REMAINDER = 0

@JvmInline
value class Money(val value: Int) {

    init {
        require(value > INITIAL_VALUE) { "돈은 $INITIAL_VALUE 보다 커야합니다." }
        require(value % MONEY_UNIT == NO_REMAINDER) { "돈은 $MONEY_UNIT 단위 입니다." }
    }
}
