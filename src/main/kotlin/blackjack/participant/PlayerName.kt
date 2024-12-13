package blackjack.participant

@JvmInline
value class PlayerName(val value: String) {
    init {
        require(value.isNotBlank()) { "이름은 공백일 수 없습니다. input = $value" }
    }
}
