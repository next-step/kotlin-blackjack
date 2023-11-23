package blackjack.model

import blackjack.model.pack.Pack

class Dealer(
    val cards: Cards = Cards(),
) : Playable {

    override fun score(): Int {
        return cards.totalScore()
    }

    override fun dealing(pack: Pack) {
        this.cards.add(pack.pickCard())
        this.cards.add(pack.pickCard())
    }

    private fun isDealerHit(): Boolean {
        return cards.totalScore() <= DEALER_PICK_THRESHOLD
    }

    fun countOfCards(): Int {
        return cards.count()
    }

    fun playing(pack: Pack): Boolean {
        if (isDealerHit()) {
            cards.add(pack.pickCard())
            return true
        }
        return false
    }

    companion object {
        private const val DEALER_PICK_THRESHOLD = 16
    }
}
