package blackject.model

/**
 * 배팅 금액 관리 클래스
 * */
data class Amount(var value: Double = MIN_AMOUNT) {
    init {
        require(value >= MIN_AMOUNT)
    }

    operator fun unaryMinus(): Double {
        return -value
    }

    companion object {
        const val MIN_AMOUNT = 0.0
    }
}
