package blackjack.domain.player

@JvmInline
value class PlayerName(
    val value: String,
) {
    init {
        require(this.value.isNotBlank()) { "이름은 공백으로 이루어질 수 없습니다." }
    }
}
