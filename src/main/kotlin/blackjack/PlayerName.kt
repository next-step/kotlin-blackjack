package blackjack

@JvmInline
value class PlayerName(val name: String) {
    init {
        require(name.isNotBlank()) { "이름은 공백일 수 없습니다. input = $name" }
    }
}