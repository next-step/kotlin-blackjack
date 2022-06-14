package blackjack.domain.participant

data class Money(
    private var _bat: Int,
) {
    val bat: Int
        get() = _bat

    init {
        require(_bat > 0) { "베팅 금액은 0원 이상이어야 합니다." }
    }

    fun accBatMoney(value: Int) {
        _bat += value
    }
}
