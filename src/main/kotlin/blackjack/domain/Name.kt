package blackjack.domain

@JvmInline
value class Name(val value: String) {
    init {
        require(value.isNotBlank()) { "빈값은 이름이 될 수 없습니다." }
    }
}
