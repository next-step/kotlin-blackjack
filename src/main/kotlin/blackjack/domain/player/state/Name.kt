package blackjack.domain.player.state

@JvmInline
value class Name(private val value: String) {
    init {
        require(value.isNotEmpty() && !value.contains(" ")) { "이름에 빈 문자열이거나 공백이 있습니다." }
        require(value.length <= MAX_PLAYER_NAME_LENGTH) { "이름은 5글자 이하만 가능합니다." }
        require(value.matches("^[a-zA-Z]*$".toRegex())) { "이름은 알파벳만 가능합니다." }
    }

    override fun toString(): String {
        return value
    }

    companion object {
        const val MAX_PLAYER_NAME_LENGTH = 5
    }
}
