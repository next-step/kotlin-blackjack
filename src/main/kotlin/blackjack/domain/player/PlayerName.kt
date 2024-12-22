package blackjack.domain.player

@JvmInline
value class PlayerName private constructor(private val name: String) {
    init {
        require(name.isNotBlank()) { "플레이어 이름은 비어있을 수 없습니다." }
        require(name.length <= MAX_LENGTH) { "플레이어 이름은 $MAX_LENGTH 자를 초과할 수 없습니다." }
    }

    override fun toString(): String = name

    companion object {
        private const val MAX_LENGTH = 10

        fun from(value: String): PlayerName {
            return PlayerName(value)
        }
    }
}
