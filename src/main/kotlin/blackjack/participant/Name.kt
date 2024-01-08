package blackjack.participant

@JvmInline
value class Name(
    val value: String
) {
    init {
        require(value.isNotBlank()) { VALID_MESSAGE }
    }

    companion object {
        private const val VALID_MESSAGE: String = "빈 값은 이름이 될 수 없습니다."
    }
}
