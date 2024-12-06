package blackjack.domain

class DealerHand : Hand() {
    override fun drawFrom(deck: Deck) {
        cards.add(deck.draw())
        if (isDrawnCardTheHoleCard()) {
            holeCard().flip()
        }
    }

    private fun holeCard() = cards[HOLE_CARD_INDEX]

    private fun isDrawnCardTheHoleCard() = cards.size == 2

    companion object {
        private const val HOLE_CARD_INDEX = 1
    }
}
