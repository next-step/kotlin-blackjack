package blackjack.domain

class Player (
    name: String,
    cardDeck: CardDeck,
) : Participant(name, cardDeck) {
    override fun isObtainable(): Boolean {
        return sumOfCards() < BLACKJACK_SCORE
    }

    override fun openCards(): List<Card> {
        return hands.subList(0, 2)
    }
}
