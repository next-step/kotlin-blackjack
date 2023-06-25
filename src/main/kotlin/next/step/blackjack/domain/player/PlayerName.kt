package next.step.blackjack.domain.player

@JvmInline
value class PlayerName(val name: String) {
    init {
        require(name.isNotBlank()) { "플레이러 이름은 공백이 아닌 글자여야 합니다." }
    }

    companion object {
        fun of(name: String) = PlayerName(name)
    }
}