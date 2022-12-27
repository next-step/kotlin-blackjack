package blackjack.model

@JvmInline
value class Bet(val value: Int) {
    init {
        require(value > 0) { "베팅 금액은 0보다 커야 합니다." }
    }

    override fun toString(): String = "$value"
}
