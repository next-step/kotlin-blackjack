package blackjack.domain

data class Player(val name: String) {
    init {
        require(name.isNotBlank()) { WRONG_PLAYER_NAME_MESSAGE }
    }

    companion object {
        private const val WRONG_PLAYER_NAME_MESSAGE = "잘못된 플레이어 이름입니다.(1글자 이상, 공백문자 금지)"
    }
}
