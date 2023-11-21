package blackjack.domain

private const val MIN_BETTING_MONEY = 1

private const val MAX_BETTING_MONEY = 1_000_000

data class BettingMoney(val value: Int) {
    init {
        require(value in MIN_BETTING_MONEY..MAX_BETTING_MONEY) { "베팅 금액은 $MIN_BETTING_MONEY 이상 $MAX_BETTING_MONEY 이하의 숫자만 가능합니다." }
    }
}
