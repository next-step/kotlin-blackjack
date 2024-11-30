package blackjack

@JvmInline
value class Name(private val value: String) {
    init {
        require(value.isNotBlank()) { "공백이 아닌 문자열 이어야 합니다." }
    }
}
