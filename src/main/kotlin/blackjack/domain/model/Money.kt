package blackjack.domain.model

@JvmInline
value class Money(val money: Int) {
    operator fun plus(amount: Money): Money = Money(this.money + amount.money)

    operator fun minus(amount: Money): Money = Money(this.money - amount.money)

    operator fun times(amount: Money): Money = Money(this.money * amount.money)
}
