package blackjack.model.player

@JvmInline
value class PlayerName(
    val name: String
) {

    init {
        validateNotEmpty(name)
    }

    private fun validateNotEmpty(name: String) = require(name.isNotBlank()) { "플레이어 이름이 공백일 수 없습니다." }
}
