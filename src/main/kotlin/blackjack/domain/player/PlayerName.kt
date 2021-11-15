package blackjack.domain.player

@JvmInline
value class PlayerName(val value: String) {

    init {
        require(value.isNotEmpty()) { "이름은 공백일 수 없습니다." }
    }

    companion object {

        fun from(names: List<String>): List<PlayerName> {
            return names.map { PlayerName(it) }
        }
    }
}
