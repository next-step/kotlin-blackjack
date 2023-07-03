package blackjack.domain.player

@JvmInline
value class Name(
    val value: String,
) {
    init {
        require(value.length <= NAME_LENGTH_LIMIT) { "플레이어 이름은 5자를 초과할 수 없습니다." }
    }

    companion object {
        private const val NAME_LENGTH_LIMIT = 5
    }
}
