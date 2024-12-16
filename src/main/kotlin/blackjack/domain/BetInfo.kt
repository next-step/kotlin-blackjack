package blackjack.domain

data class BetInfo(val name: String, val betAmount: Int) {
    init {
        require(betAmount > MIN_BET) { "베팅금액은 0보단 큰 금액을 넣어야합니다." }
    }

    companion object {
        private const val MIN_BET = 0
    }
}
