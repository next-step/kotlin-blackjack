package blackjack.domain

@JvmInline
value class Name(val value: String) {
    init {
        require(value.isNotBlank()) { "이름은 빈 값일 수 없습니다." }
    }
}
