package blackjack.core.player

class BettingAmount(val value: Int) {
    constructor(amount: String) : this(amount.toIntOrNull() ?: throw IllegalArgumentException(ERROR_INVALID))

    init {
        require(value >= 0) { ERROR_INVALID }
    }

    operator fun minus(other: BettingAmount): BettingAmount {
        return BettingAmount(this.value - other.value)
    }

    operator fun plus(other: BettingAmount): BettingAmount {
        return BettingAmount(this.value + other.value)
    }


    companion object {
        private const val ERROR_INVALID = "잘못된 값입니다"
    }
}
