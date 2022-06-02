package blackjack.domain.player.vo

@JvmInline
value class Name(
    val value: String
) {
    init {
        require(value.isNotEmpty()) { "이름은 비어있을수 없습니다." }
    }
}
