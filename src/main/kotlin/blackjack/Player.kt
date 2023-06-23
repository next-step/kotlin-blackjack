package blackjack

data class Player(
    val name: String,
    var cards: Cards,
) {
    fun hit(card: Card) {
        val addedCards = cards + card
        if (addedCards.calculateScore() <= Cards.WINNING_NUMBER) {
            cards = addedCards
        }
    }

    fun calculateScore(): Int = cards.calculateScore()
}
