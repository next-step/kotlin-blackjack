package blackjack.core.amount

open class Amount(protected var value: Int = 0) {
    val amount
        get() = value

    init {
        require(value >= 0) { ERROR_INVALID }
    }

    companion object {
        const val ERROR_INVALID = "잘못된 값입니다"
    }
}
