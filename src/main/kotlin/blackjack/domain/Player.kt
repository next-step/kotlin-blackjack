package blackjack.domain

class Player(
    name: String,
    card1: Card,
    card2: Card,
) : Participant(name, card1, card2) {

    override fun isObtainable(): Boolean {
        return sumOfCards() < BLACKJACK_SCORE
    }

    override fun openCards(): List<Card> {
        return hands.subList(0, 2)
    }
}
