package blackjack.domain

import blackjack.common.Policy

class Dealer(
    name: String,
    cards: Cards = Cards(),
) : Participant(
    name = name,
    cards = cards,
) {
    override fun canHit(): Boolean {
        return cards.getScore() <= Policy.DEALER_INIT_MAX_SCORE
    }
}
