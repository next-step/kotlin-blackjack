package blackjack.domain

class Player(
    name: String,
    cards: Cards = Cards(),
) : Participant(
    name = name,
    cards = cards,
) {
    override fun canHit(): Boolean {
        return !((super.isBust() || super.isBlackJack() || super.isMaxScore()))
    }
}
