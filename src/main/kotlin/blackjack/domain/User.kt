package blackjack.domain

@JvmInline
value class User(val name: String) {
    init {
        require(name.isNotBlank()) { EMPTY_NAME_ERROR_MESSAGE }
    }

    companion object {
        private const val EMPTY_NAME_ERROR_MESSAGE = "이름이 비어있을 수 없습니다"
    }
}
