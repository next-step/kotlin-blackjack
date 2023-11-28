package blackjack.domain

class Player (
    name: String,
    cardDeck: CardDeck,
) : Participant(name, cardDeck) {
    override val openedCards = hands.subList(0, 2)

    override fun isObtainable(): Boolean {
        return sumOfCards() < BLACKJACK_SCORE
    }
}
