package blackjack.domain.bet

@JvmInline
value class Bet(private val value: Int) {
    constructor(value: String) : this(value.toIntOrNull() ?: throw IllegalArgumentException("숫자 이외의 값은 입력할 수 없습니다."))

    fun toDouble(): Double = value.toDouble()
}
