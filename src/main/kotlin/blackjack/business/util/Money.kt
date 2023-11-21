package blackjack.business.util

data class Money(val value: Int = MIN_VALUE) {

    init {
        require(value >= MIN_VALUE) { "돈은 최소값보다 작을 수 없습니다." }
    }

    fun lose(): Int = -value

    companion object {
        const val MIN_VALUE = 0
    }
}
