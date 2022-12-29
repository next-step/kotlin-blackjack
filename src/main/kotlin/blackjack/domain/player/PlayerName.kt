package blackjack.domain.player

class PlayerName(
    private val value: String
) {
    init {
        require(value.trim() == value) { "이름에 공백이 없어야합니다." }
    }

    fun getValue() = value
}
