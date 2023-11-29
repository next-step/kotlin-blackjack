package blackjack.domain

@JvmInline
value class Amount(val value: Int) {
    init {
        require(value >= 1) { "베팅 금액은 1 이상이어야 합니다." }
    }

    operator fun plus(amount: Amount): Amount = Amount(this.value + amount.value)

    operator fun minus(amount: Amount): Amount = Amount(this.value - amount.value)

    operator fun compareTo(amount: Amount): Int = this.value.compareTo(amount.value)
}
