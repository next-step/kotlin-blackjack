package blackjack.domain.participant

data class Money(
    val bat: Int,
) {
    private var _profitPercentage: Int = 0
    val profitPercentage: Int
        get() = _profitPercentage

    init {
        require(bat > 0) { "베팅 금액은 0원 이상이어야 합니다." }
    }

    fun setProfitPercentage(value: Int) {
        _profitPercentage = value
    }

    fun getFinalEarnings(): Int {
        return (bat * _profitPercentage * 0.01).toInt()
    }
}
