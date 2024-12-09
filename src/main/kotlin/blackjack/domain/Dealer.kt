package blackjack.domain

class Dealer : Participant() {
    companion object {
        private const val MUST_DRAW_THRESHOLD = 16
        private const val BUST_THRESHOLD = 21
    }

    fun playTurn(deck: Deck) {
        while (getTotalValue() <= MUST_DRAW_THRESHOLD) {
            addCard(deck.draw())
        }
    }

    fun distributeInitialCards(deck: Deck) {
        repeat(2) { addCard(deck.draw()) }
    }

    fun isBust(): Boolean {
        return getTotalValue() > BUST_THRESHOLD
    }
}
