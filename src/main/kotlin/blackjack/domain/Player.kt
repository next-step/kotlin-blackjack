package blackjack.domain

data class Player(
    val name: String,
    val cards: Cards
) {
    init {
        require(name.isNotEmpty() && name.isNotBlank())
    }
}