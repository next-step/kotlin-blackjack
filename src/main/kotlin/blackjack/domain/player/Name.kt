package blackjack.domain.player

class Name(val value: String) {
    init {
        require(value.trim().isNotEmpty()) { "Name can't be empty." }
    }
}
