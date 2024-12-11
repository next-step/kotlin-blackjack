package blackjack.domain.player

import blackjack.domain.card.Card

class Dealer : Player(
    DEALER_NAME,
) {
    override fun canHit(): Boolean = hand.score < DEALER_SCORE_LIMIT

    override fun hit(card: Card) = hand.add(card)

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_SCORE_LIMIT = 17
    }
}
