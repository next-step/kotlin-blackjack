package game.blackjack.v1.domain

class Dealer : Participant("딜러") {

    fun drawCardIfRequired(
        deck: Deck,
        result: () -> Unit = {}
    ) {
        if (isDrawRequired()) {
            drawCard(deck.drawOnce())
            result()
        }
    }

    private fun isDrawRequired(): Boolean {
        return getScore() <= DEALER_HIT_THRESHOLD
    }

    fun toFinalResult() = toString() + " - 결과: ${getScore()}"
    
    companion object {
        private const val DEALER_HIT_THRESHOLD = 16
    }
}
