package domain

/**
 * 베팅금액을 위한 Value Class
 */
@JvmInline
value class Money private constructor(val amount: Int) {
    init {
        require(amount >= 0) { "금액은 0 이상이어야 합니다." }
    }

    companion object {
        fun of(amount: Int): Money = Money(amount)
    }

    operator fun plus(otherMoney: Money): Money = of(this.amount + otherMoney.amount)
    operator fun minus(otherMoney: Money): Money {
        val result = this.amount - otherMoney.amount
        require(result >= 0) { "금액은 0 미만이 될 수 없습니다." }
        return of(result)
    }
}

/**
 * 수익(profit)의 경우 음수가 될 수 있으니 SignedMoney로 표현
 */
@JvmInline
value class SignedMoney(val amount: Int) {
    operator fun plus(otherMoney: SignedMoney): SignedMoney = SignedMoney(this.amount + otherMoney.amount)
    operator fun unaryMinus(): SignedMoney = SignedMoney(-amount)
}