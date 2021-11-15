package blackjack.model

data class Player(val name: Name, val cards: Cards) {

    fun receive(card: Card): Player = copy(cards = cards + card)
}
