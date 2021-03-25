package blackjack.domain

data class PlayerName(
    val name: String
) {
    init {
        require(name.isNotBlank()) {
            "유효하지 않은 플레이어 이름이 입력되었습니다."
        }
    }
}
