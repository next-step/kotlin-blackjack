package blackjack.domain

class Name(private val value: String) {
    init {
        require(value.trim().isNotEmpty()) { "Name can't be empty." }
    }
}
