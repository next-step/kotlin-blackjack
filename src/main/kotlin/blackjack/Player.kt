package blackjack

data class Player(
    val name: String,
    var cards: Cards,
) {
    fun addCard(card: Card) {
        cards += card
    }
}
