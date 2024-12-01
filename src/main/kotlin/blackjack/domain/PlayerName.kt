package blackjack.domain

@JvmInline
value class PlayerName(val value: String) {
    init {
        require(value.isNotBlank()) { "이름은 빈 칸이 될 수 없습니다." }
    }
}
