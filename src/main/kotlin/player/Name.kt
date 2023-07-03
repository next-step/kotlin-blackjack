package player

@JvmInline
value class Name(
    val value: String,
) {
    init {
        require(value.length <= NAME_LENGTH_LIMIT) { "플레이어의 이름은 5글자를 초과할 수 없습니다." }
    }

    companion object {
        private const val NAME_LENGTH_LIMIT = 5
    }
}
