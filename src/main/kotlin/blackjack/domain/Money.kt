package blackjack.domain

class Money(val value: Int) {
    init {
        require(value >= 0) { "금액은 0보다 커야 합니다." }
    }
}
