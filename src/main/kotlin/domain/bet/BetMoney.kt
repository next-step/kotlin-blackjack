package domain.bet

@JvmInline
value class BetMoney(val amount: Int) {
    init {
        require(amount > 0) { "베팅 금액은 0보다 커야 합니다." }
    }
}
