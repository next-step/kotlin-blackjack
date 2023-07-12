package blackjack.domain.participant

@JvmInline
value class BetAmount(val value: Int) {
    init {
        require(value >= 0) { "베팅 금액은 0원 이상입니다." }
    }

    companion object {
        fun of(value: String): BetAmount {
            return BetAmount((value.toInt()))
        }
    }
}
