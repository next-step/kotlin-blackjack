package blakjack.domain

@JvmInline
value class Money(val value: Int) {
    operator fun times(value: Double): Money = Money((this.value * value).toInt())
    fun negate(): Money = Money(-value)

    companion object {
        val ZERO: Money = Money(0)
    }
}
