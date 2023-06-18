package blackjack.domain.player

@JvmInline
value class PlayerName(val name: String) {

    init {
        require(value = name.length in NAME_LENGTH_RANGE) {
            "사용 가능한 이름 범위가 아닙니다. 범위 : $NAME_LENGTH_RANGE, 입력한 이름 : $name"
        }
    }

    companion object {
        private val NAME_LENGTH_RANGE: IntRange = 1..10
    }
}
