package blackjack.domain.player

data class Name(private val value: String) {
    init {
        require(value.isNotEmpty()) { "이름은 1글자 이상이다." }
    }
}
