package blackjack.domain

class DealerHand : Hand() {
    override fun drawFrom(deck: Deck) {
        cards.add(deck.draw())
        if (isDrawnCardTheHoleCard()) {
            holeCard().flip()
        }
    }

    fun flipHoleCardUp() {
        checkHasHoleCard()
        checkHoleCardFaceIsUp()
        holeCard().flip()
    }

    private fun checkHasHoleCard() {
        check(cards.size >= INITIAL_HAND_SIZE) { "두번째 카드가 없습니다." }
    }

    private fun checkHoleCardFaceIsUp() {
        check(!holeCard().isFaceUp) { "이미 앞면이 보이는 카드입니다." }
    }

    private fun holeCard() = cards[HOLE_CARD_INDEX]

    private fun isDrawnCardTheHoleCard() = cards.size == INITIAL_HAND_SIZE

    companion object {
        private const val HOLE_CARD_INDEX = 1
    }
}
