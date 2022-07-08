package blackjack.domain.participant

data class BetAmount(
    val value: Double
) {
    init {
        require(value >= 0) { "베팅 금액은 음수일 수 없습니다." }
    }
}
