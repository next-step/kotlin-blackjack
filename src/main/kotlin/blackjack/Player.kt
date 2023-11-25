package blackjack

class Player(
    val name: String,
    card1: Card,
    card2: Card,
) {
    val cards = Cards(card1, card2)

    fun acquire(card: Card) {
        cards.add(card)
    }

    fun sumOfCards(): Int {
        return cards.sum()
    }
}
