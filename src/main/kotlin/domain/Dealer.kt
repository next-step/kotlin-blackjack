package domain

class Dealer(): Participant() {
    override fun openFirstRound(): List<Card> {
        return blackjackCards.getCards().firstOrNull()?.let { listOf(it) }?: emptyList()
    }
}
