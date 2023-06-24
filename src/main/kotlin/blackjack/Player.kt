package blackjack

class Player(
    name: String,
    cards: Cards,
) : Participant(name, cards) {
    override fun openedCards(): Cards = cards
}
