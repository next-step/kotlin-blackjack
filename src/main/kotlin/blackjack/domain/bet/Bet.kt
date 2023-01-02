package blackjack.domain.bet

@JvmInline
value class Bet(val value: Int) {
    init {
        require(value > 0) { "베팅 금액은 0보다 커야 합니다." }
    }

    constructor(value: String) : this(value.toIntOrNull() ?: throw IllegalArgumentException("숫자 이외의 값은 입력할 수 없습니다."))
}
