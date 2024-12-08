package blackjack.core.player

class Name(private val name: String) {
    init {
        require(name.isNotBlank()) { ERROR_NAME_IS_BLANK }
    }

    override fun toString(): String {
        return name.trim()
    }

    companion object {
        private const val ERROR_NAME_IS_BLANK = "이름이 비어있습니다."
    }
}
