package blackjack

class Player(
    val name: String,
    card1: Card,
    card2: Card,
) {
    val cards = Cards(card1, card2)
}
