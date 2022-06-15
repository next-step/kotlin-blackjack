package blackjack.domain

@JvmInline
value class BetMoney(private val amount: Int){
    init {
        require(amount >= 0) { "금액은 0보다 커야 합니다" }
    }
}
