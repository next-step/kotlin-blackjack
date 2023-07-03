package blackjack.domain.player

@JvmInline
value class Money(
    val value: Int,
) {
    init {
        require(value > 0) { "돈은 0원 이하가 될 수 없다." }
    }

    operator fun times(scale: Double): Money = Money((value * scale).toInt())
}
