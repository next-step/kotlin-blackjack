package blackjack

class Player(val name: String, val cards: Cards) {
    fun takeCard(card: Card) {
        cards.takeCard(card)
    }

    fun score(): Int = cards.sum()
}
