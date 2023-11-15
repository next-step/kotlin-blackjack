package blackjack.model

data class Player(
    val name: String,
    val cards: Cards = Cards.init(),
) {
    fun deal(card1: Card, card2: Card) {
        cards.add(card1)
        cards.add(card2)
    }

    fun hit(card: Card) {
        cards.add(card)
    }
}
