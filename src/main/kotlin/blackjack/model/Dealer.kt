package blackjack.model

import blackjack.model.pack.Pack

data class Dealer(
    val cards: Cards = Cards.emptyCards(),
) {
    fun play(pack: Pack) {
        if (isDealerHit()) {
            cards.add(pack.pickCard())
        }
    }

    private fun isDealerHit(): Boolean {
        return cards.totalScore() <= DEALER_PICK_THRESHOLD
    }

    fun countOfCards(): Int {
        return cards.count()
    }

    companion object {
        private const val DEALER_PICK_THRESHOLD = 16;
    }
}
