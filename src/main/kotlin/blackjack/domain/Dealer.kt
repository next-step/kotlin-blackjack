package blackjack.domain

class Dealer(val cards: Cards = Cards()) {
    val cardSet get() = cards.cards

    fun drawBy(trumpCard: TrumpCard) {
        cards.add(trumpCard.draw())
    }

    fun isHit(): Boolean {
        return cards.score() < Score(DEALER_HIT_SCORE)
    }

    companion object {
        private const val DEALER_HIT_SCORE = 17
    }
}
