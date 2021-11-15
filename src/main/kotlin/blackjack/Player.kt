package blackjack

data class Player(val name: String, val cards: Cards) {

    init {
        require(name.isNotEmpty())
    }
}
