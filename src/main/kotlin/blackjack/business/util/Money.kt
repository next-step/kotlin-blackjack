package blackjack.business.util

data class Money(private val value: Int) {
    init {
        require(value >= 0) { "금액은 0 이상이여야 합니다." }
    }
}
