package blackjack

data class Player(val name: String, val cards: Cards) {

    init {
        require(name.isNotEmpty())
    }

    fun receive(card: Card): Player = copy(cards = cards + card)
}
