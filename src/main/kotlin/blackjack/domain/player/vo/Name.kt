package blackjack.domain.player.vo

private val NAME_LENGTH_RANGE = 2..5

@JvmInline
value class Name(val value: String) {
    init {
        require(value.length in NAME_LENGTH_RANGE) { "이름의 길이는 ${NAME_LENGTH_RANGE}자 사이여야 합니다." }
    }
}
