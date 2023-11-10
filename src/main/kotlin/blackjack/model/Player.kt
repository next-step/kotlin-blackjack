package blackjack.model

data class Player(
    val name: String,
    val cards: Cards = Cards.init(),
) {
    fun deal(card: Card) {
        cards.add(card)
    }
}
