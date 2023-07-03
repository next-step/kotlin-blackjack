package blackjack.domain.player

@JvmInline
value class Money(
    val value: Int,
) {
    init {
        require(value > 0) { "돈은 0원 이하가 될 수 없습니다." }
    }

    fun times(scale: Double): Int = (value * scale).toInt()

    fun times(scale: Int): Int = value * scale
}
