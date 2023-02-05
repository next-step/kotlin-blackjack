package blackjack.domain

import blackjack.common.Policy

class Player(
    name: String,
    cards: Cards = Cards(),
) : Participant(
    name = name,
    cards = cards,
) {
    override fun canHit(): Boolean {
        cards.getScore() < Policy.BUST_SCORE
        return !((super.isBust() || super.isBlackJack() || super.isMaxScore()))
    }
}
