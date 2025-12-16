package domain

class Player(val name: String): Participant() {
    override fun openFirstRound(): List<Card> {
        return blackjackCards.getCards()
    }
}
